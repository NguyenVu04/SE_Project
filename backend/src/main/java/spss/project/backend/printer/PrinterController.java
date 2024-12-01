package spss.project.backend.printer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import spss.project.backend.Environment;
import spss.project.backend.document.DocumentService;
import spss.project.backend.document.PaperSize;
import spss.project.backend.history.printing.PrintingHistoryService;
import spss.project.backend.order.Order;
import spss.project.backend.order.OrderService;
import spss.project.backend.user.student.StudentService;

/**
 * A controller for handling printer-related requests.
 */
@RestController
@CrossOrigin(origins = { Environment.FRONTEND_URL })
@RequestMapping("/printer")
public class PrinterController {
    /**
     * Prevent direct instantiation.
     */
    protected PrinterController() {
    }

    /**
     * The service for interacting with the printer database.
     */
    @Autowired
    private PrinterService service;

    /**
     * The service for interacting with the order database.
     */
    @Autowired
    private OrderService orderService;

    /**
     * The service for interacting with the student database.
     * 
     * This service is used to validate that the student making the request exists
     * and has enough balance to print the requested documents.
     */
    @Autowired
    private StudentService studentService;

    /**
     * The service for interacting with the document database.
     */
    @Autowired
    private DocumentService documentService;

    /**
     * The service for interacting with the printing history database.
     */
    @Autowired
    private PrintingHistoryService printingHistoryService;

    /**
     * The logger for this controller.
     */
    private static final Logger logger = LoggerFactory.getLogger(PrinterController.class);

    /**
     * Retrieves all printers from the database.
     * 
     * @return a list of all printers in the database
     */
    @Operation(description = "Retrieves all printers from the database", summary = "Retrieves all printers from the database", responses = {
            @ApiResponse(responseCode = "200", description = "Printers retrieved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = Printer.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error getting all printer")
    })
    @GetMapping("all")
    public ResponseEntity<Object> getAllPrinters() {
        try {
            return ResponseEntity.ok().body(service.getPrinters());
        } catch (Exception e) {
            logger.error("Error getting all printer", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Retrieves all active printers from the database.
     * 
     * @return a list of all active printers in the database
     */
    @Operation(description = "Retrieves all active printers from the database", summary = "Retrieves all active printers from the database", responses = {
            @ApiResponse(responseCode = "200", description = "Printers retrieved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = Printer.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error getting active printer")
    })
    @GetMapping("active")
    public ResponseEntity<Object> getActivePrinters() {
        try {
            return ResponseEntity.ok().body(service.getActivePrinters());
        } catch (Exception e) {
            logger.error("Error getting active printer", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Handles a message from a printer about the status of a print job.
     *
     * @param message a map containing the order ID and a boolean indicating
     *                whether the print job was successful
     * @return a success response if the message was processed successfully
     */
    @Operation(description = "Handles a message from a printer about the status of a print job", summary = "Handles a message from a printer about the status of a print job", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the printer message", required = true, content = {
            @Content(examples = {
                    @ExampleObject(name = "Printer Message", value = "{ \"orderId\": \"123\", \"successful\": true, \"timeReceived\": \"2022-01-01T12:34:56\", \"timePrinted\": \"2022-01-01T12:34:56\" }")
            }, mediaType = "application/json")
    }), responses = {
            @ApiResponse(responseCode = "200", description = "Message processed successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "400", description = "Invalid printer message"),
            @ApiResponse(responseCode = "500", description = "Error processing message")
    })
    @PostMapping("message")
    public ResponseEntity<Object> getMessage(@RequestBody Map<String, Object> message) {
        try {

            String orderId = (String) message.get("orderId");
            boolean successful = (boolean) message.get("successful");
            LocalDateTime timeReceived = LocalDateTime.parse((String) message.get("timeReceived"), DateTimeFormatter.ISO_DATE_TIME);
            LocalDateTime timePrinted = LocalDateTime.parse((String) message.get("timePrinted"), DateTimeFormatter.ISO_DATE_TIME);

            Order order = orderService.updateOrderStatus(orderId, true);
            PaperSize paperSize = PaperSize.valueOf(order.getPaperSize());

            printingHistoryService.save(
                    order.getStudentId(),
                    order.getPrinterId(),
                    order.getDocumentId(),
                    paperSize,
                    order.getPageNumbers(),
                    order.getNumberOfCopies(),
                    order.isSingleSided(),
                    order.getTimeOrdered(),
                    timeReceived,
                    timePrinted,
                    successful);

            if (!successful) {
                studentService.addBalance(orderId, order.getCost());
            }

            return ResponseEntity.ok().build();

        } catch (NotFoundException e) {

            logger.error("Order not found", e);
            return ResponseEntity.notFound().build();

        } catch (ClassCastException | NullPointerException e) {

            logger.error("Error parsing printer message", e);
            return ResponseEntity.badRequest().build();

        } catch (Exception e) {

            logger.error("Error processing printer message", e);
            return ResponseEntity.internalServerError().build();

        }
    }

    /**
     * Retrieves all orders which were submitted to the given printer and have not
     * yet
     * been printed.
     * 
     * @param printerId the id of the printer to find the orders for
     * @return the orders of the printer which have not yet been printed, sorted by
     *         the time
     *         at which the orders were submitted in descending order
     */
    @Operation(description = "Retrieves all orders which were submitted to the given printer and have not yet been printed", summary = "Retrieves all orders which were submitted to the given printer and have not yet been printed", parameters = {
            @Parameter(name = "printerId", description = "The id of the printer to find the orders for", required = true, in = ParameterIn.PATH)
    }, responses = {
            @ApiResponse(responseCode = "200", description = "Orders retrieved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "[{ \"orderId\": \"123\", \"documentUrl\": \"https://example.com/document.pdf\", \"paperSize\": \"A4\", \"pageNumbers\": 1, \"numberOfCopies\": 1, \"singleSided\": true }]"))
            }),
            @ApiResponse(responseCode = "500", description = "Error getting printer orders")
    })
    @GetMapping("orders")
    public ResponseEntity<Object> getPrinterOrders(
            @RequestParam("printerId") String printerId) {
        try {
            List<Order> orders = orderService.getPrinterOrders(printerId);
            List<Map<String, Object>> response = new ArrayList<>(orders.size());

            for (Order order : orders) {
                Map<String, Object> orderMap = new HashMap<>();
                orderMap.put("orderId", order.getId());
                orderMap.put("documentUrl",
                        documentService.getDocumentUrl(order.getStudentId(), order.getDocumentId()));
                orderMap.put("paperSize", order.getPaperSize());
                orderMap.put("pageNumbers", order.getPageNumbers());
                orderMap.put("numberOfCopies", order.getNumberOfCopies());
                orderMap.put("singleSided", order.isSingleSided());
                response.add(orderMap);
            }
            return ResponseEntity.ok().body(response);

        } catch (Exception e) {

            logger.error("Error getting printer orders", e);
            return ResponseEntity.internalServerError().build();

        }
    }
}

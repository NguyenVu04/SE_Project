package spss.project.backend.printer;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RestController;

import spss.project.backend.Environment;
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
    protected PrinterController() {}

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
    @PostMapping("message")
    public ResponseEntity<Object> getMessage(@RequestBody Map<String, Object> message) {
        try {

            String orderId = (String) message.get("orderId");
            boolean successful = (boolean) message.get("successful");

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
                    LocalDateTime.now(),
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
}

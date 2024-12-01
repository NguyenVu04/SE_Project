package spss.project.backend.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import jakarta.validation.ConstraintViolationException;
import spss.project.backend.Environment;
import spss.project.backend.document.PaperSize;
import spss.project.backend.printer.Printer;
import spss.project.backend.printer.PrinterService;
import spss.project.backend.user.student.StudentService;

/**
 * Handles all requests related to orders.
 */
@RestController
@CrossOrigin(origins = { Environment.FRONTEND_URL })
@RequestMapping("order")
public class OrderController {

    /**
     * Creates a new order controller.
     */
    protected OrderController() {
    }

    /**
     * The service used to access and manipulate orders.
     */
    @Autowired
    private OrderService service;

    /**
     * The service used to access and manipulate printers.
     */
    @Autowired
    private PrinterService printerService;

    /**
     * The service used to access and manipulate students.
     */
    @Autowired
    private StudentService studentService;

    /**
     * Logger for log messages.
     */
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    /**
     * Retrieves all orders submitted by a given student.
     * 
     * @param id the id of the student to find the orders for
     * @return the orders of the student, sorted by the time at which the
     *         orders were submitted in descending order
     */
    @Operation(description = "Retrieves all orders submitted by a given student", summary = "Retrieves all orders submitted by a given student", parameters = {
            @Parameter(name = "id", description = "The id of the student to find the orders for", required = true, in = ParameterIn.QUERY)
    }, responses = {
            @ApiResponse(responseCode = "200", description = "Orders retrieved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = Order.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error getting student orders")
    })
    @GetMapping("all/student")
    public ResponseEntity<Object> getStudentOrders(
            @RequestParam("id") String id) {
        try {

            return ResponseEntity.ok().body(service.getStudentOrders(id));

        } catch (Exception e) {

            logger.error("Error getting student orders", e);
            return ResponseEntity.internalServerError().build();

        }
    }

    /**
     * Adds a new order to the database for a given student.
     * 
     * @param body the request body containing the studentId, printerId, fileName,
     *             paperSize, pageNumbers, singleSided, and numberOfCopies of the
     *             order to add
     * @return a success response if the order was added successfully
     */
    @Operation(description = "Add a new order", summary = "Add order", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the order to be added", required = true, content = {
            @Content(examples = @ExampleObject(name = "order sample", value = "{\n" +
                    "    \"studentId\": \"student1@hcmut.edu.vn\",\n" +
                    "    \"printerId\": \"printer1\",\n" +
                    "    \"filename\": \"document.pdf\",\n" +
                    "    \"paperSize\": \"A4\",\n" +
                    "    \"pageNumbers\": [1, 2, 3],\n" +
                    "    \"singleSided\": true,\n" +
                    "    \"numberOfCopies\": 2\n" +
                    "}"))
    }), responses = {
            @ApiResponse(responseCode = "200", description = "Order added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "503", description = "Student balance not enough")
    })
    @PostMapping("student")
    public ResponseEntity<Object> addStudentOrders(
            @RequestBody Map<String, Object> body) {
        try {
            String studentId = (String) body.get("studentId");
            String printerId = (String) body.get("printerId");
            String filename = (String) body.get("filename");
            PaperSize paperSize = PaperSize.valueOf((String) body.get("paperSize"));
            @SuppressWarnings("unchecked")
            List<Integer> pageNumbers = (ArrayList<Integer>) body.get("pageNumbers");
            boolean singleSided = (boolean) body.get("singleSided");
            int numberOfCopies = (int) body.get("numberOfCopies");

            Order order = service.save(
                    studentId,
                    printerId,
                    filename,
                    paperSize.toString(),
                    pageNumbers,
                    numberOfCopies,
                    singleSided,
                    LocalDateTime.now(),
                    false);

            studentService.addBalance(studentId, -order.getCost());

            Printer printer = printerService.getPrinter(printerId);
            if (!printer.isActive()) {
                throw new IllegalArgumentException();
            }

            return ResponseEntity.ok().build();
        } catch (ConstraintViolationException e) {

            logger.error("Student balance not enough", e);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();

        } catch (NullPointerException e) {

            logger.error("Resource not found", e);
            return ResponseEntity.badRequest().build();

        } catch (ClassCastException e) {

            logger.error("Error parsing student orders", e);
            return ResponseEntity.badRequest().build();

        } catch (IllegalArgumentException e) {

            logger.error("Paper size not found", e);
            return ResponseEntity.badRequest().build();

        } catch (Exception e) {

            logger.error("Error adding student orders", e);
            return ResponseEntity.internalServerError().build();

        }
    }

    /**
     * Deletes an order by its id.
     * 
     * @param id the id of the order to delete
     * @return a success response if the order was deleted successfully
     */
    @Operation(description = "Deletes an order by their ID", summary = "Deletes an order by their ID", parameters = {
            @Parameter(name = "id", description = "The ID of the order to delete", required = true, in = ParameterIn.QUERY)
    }, responses = {
            @ApiResponse(description = "Order deleted successfully", responseCode = "200"),
            @ApiResponse(description = "Error occurred while deleting the order", responseCode = "500")
    })
    @DeleteMapping("")
    public ResponseEntity<Object> deleteOrder(@RequestParam("id") String id) {
        try {
            service.deleteOrder(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error deleting order", e);
            return ResponseEntity.internalServerError().build();
        }   
    }
}

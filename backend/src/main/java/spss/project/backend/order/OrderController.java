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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spss.project.backend.Environment;
import spss.project.backend.document.PaperSize;
import spss.project.backend.printer.Printer;
import spss.project.backend.printer.PrinterService;
import spss.project.backend.user.student.Student;
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
    @PostMapping("student")
    public ResponseEntity<Object> addStudentOrders(
            @RequestBody Map<String, Object> body) {
        try {
            String studentId = (String) body.get("studentId");
            String printerId = (String) body.get("printerId");
            String fileName = (String) body.get("fileName");
            PaperSize paperSize = PaperSize.valueOf((String) body.get("paperSize"));
            @SuppressWarnings("unchecked")
            List<Integer> pageNumbers = (ArrayList<Integer>) body.get("pageNumbers");
            boolean singleSided = (boolean) body.get("singleSided");
            int numberOfCopies = (int) body.get("numberOfCopies");

            Student student = studentService.getStudent(studentId);
            double orderCost = paperSize.getValue() * numberOfCopies * pageNumbers.size();

            if (student.getBalance() < orderCost) {
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
            }

            service.save(
                    studentId,
                    printerId,
                    fileName,
                    paperSize.toString(),
                    pageNumbers,
                    numberOfCopies,
                    singleSided,
                    LocalDateTime.now(),
                    false);

            Printer printer = printerService.getPrinter(printerId);
            if (!printer.isActive()) {
                throw new IllegalArgumentException();
            }
            //TODO: uncomment when printer is ready
            // printer.printDocument(
            //         documentService.convertToFileUrl(fileName, studentId),
            //         paperSize,
            //         pageNumbers,
            //         singleSided,
            //         numberOfCopies);

            return ResponseEntity.ok().build();
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

}

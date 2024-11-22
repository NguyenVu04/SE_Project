package spss.project.backend.history.printing;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spss.project.backend.Environment;

/**
 * Handles all requests related to the printing history of the students.
 */
@RestController
@CrossOrigin(origins = { Environment.FRONTEND_URL })
@RequestMapping("history/printing")
public class PrintingHistoryController {

    /**
     * The default constructor.
     */
    protected PrintingHistoryController() {}

    /**
     * The service used to access the printing history items in the database.
     */
    @Autowired
    private PrintingHistoryService service;

    /**
     * The logger used to log messages from this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(PrintingHistoryController.class);

    /**
     * Retrieves all printing history items which were printed on the given printer
     * between the given times.
     * 
     * @param printerId the id of the printer on which the printing jobs were
     *                  printed
     * @param from      the start time of the range (inclusive)
     * @param to        the end time of the range (inclusive)
     * @return a list of all printing history items which were printed on the
     *         given printer between the given times, sorted by the time at which
     *         the printing jobs were completed in descending order
     */
    @GetMapping("/printer")
    public ResponseEntity<Object> getPrinterPrintingHistory(
            @RequestParam("printerId") String printerId,
            @RequestParam("from") String from,
            @RequestParam("to") String to) {
        try {

            LocalDateTime fromTime = LocalDateTime.parse(from);
            LocalDateTime toTime = LocalDateTime.parse(to);
            return ResponseEntity.ok()
                    .body(service.getPrinterPrintingHistory(
                            printerId,
                            fromTime,
                            toTime));

        } catch (ClassCastException e) {

            logger.error("Error parsing printing history data", e);
            return ResponseEntity.badRequest().build();

        } catch (Exception e) {

            logger.error("Error getting printing history", e);
            return ResponseEntity.internalServerError().build();

        }
    }

    /**
     * Retrieves the printing history for a given student.
     *
     * @param studentId the ID of the student whose printing history is to be
     *                  retrieved
     * @return a ResponseEntity containing the list of the student's printing
     *         history items
     *         or an error response if an exception occurs
     */
    @GetMapping("student")
    public ResponseEntity<Object> getStudentPrintingHistory(
            @RequestParam("studentId") String studentId) {

        try {
            return ResponseEntity.ok()
                    .body(service.getStudentPrintingHistory(studentId));
        } catch (Exception e) {

            logger.error("Error getting printing history", e);
            return ResponseEntity.internalServerError().build();

        }
    }
}

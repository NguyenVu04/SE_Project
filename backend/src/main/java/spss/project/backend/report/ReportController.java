package spss.project.backend.report;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spss.project.backend.Environment;

/**
 * Handles all requests related to reports.
 * Reports are created using the XSSFWorkbook class and are stored in GridFS
 * using the GridFSOperations class. The reports are retrieved from GridFS and
 * returned as a byte array.
 */
@RestController
@CrossOrigin(origins = { Environment.FRONTEND_URL })
@RequestMapping("/report")
public class ReportController {

    /**
     * Protected constructor for ReportController
     * used for prevent direct instantiation.
     */
    protected ReportController() {}

    @Autowired
    private ReportService service;

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    /**
     * Retrieves a report by its filename.
     *
     * @param reportName the name of the report file to retrieve
     * @return the report as a byte array wrapped in a ResponseEntity
     */
    @GetMapping("")
    public ResponseEntity<Object> getReport(@RequestParam("filename") String reportName) {
        try {
            return ResponseEntity.ok()
                    .headers(header -> {
                        header.add("Content-Disposition", "attachment; filename=\"" + reportName + "\"");
                        header.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    })
                    .body(service.getReport(reportName));
        } catch (Exception e) {
            logger.error("Error getting report", e);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves information about all reports.
     *
     * @return a list of all reports' information wrapped in a ResponseEntity
     */
    @GetMapping("/all")
    public ResponseEntity<Object> getAllReportsInfo() {
        try {
            return ResponseEntity.ok().body(service.getAllReports());
        } catch (Exception e) {
            logger.error("Error getting all reports", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Creates a report for the specified time range.
     *
     * @param from the start time of the report in string format
     * @param to   the end time of the report in string format
     * @return the name of the created report wrapped in a ResponseEntity
     */
    @PostMapping("")
    public ResponseEntity<Object> createReport(@RequestParam("from") String from, @RequestParam("to") String to) {
        try {
            LocalDateTime fromTime = LocalDateTime.parse(from);
            LocalDateTime toTime = LocalDateTime.parse(to);
            return ResponseEntity.ok().body(service.createReport(fromTime, toTime));
        } catch (DateTimeParseException e) {
            logger.error("Error parsing date", e);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.error("Error creating report", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

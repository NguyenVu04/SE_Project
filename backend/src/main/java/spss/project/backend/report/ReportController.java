package spss.project.backend.report;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    protected ReportController() {
    }

    @Autowired
    private ReportService service;

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    /**
     * Retrieves a report by its filename.
     *
     * @param reportName the name of the report file to retrieve
     * @return the report as a byte array wrapped in a ResponseEntity
     */
    @Operation(description = "Retrieves a report by its filename", summary = "Retrieves a report by its filename", parameters = {
            @Parameter(name = "filename", description = "The name of the report file to retrieve", required = true, in = ParameterIn.QUERY)
    }, responses = {
            @ApiResponse(responseCode = "200", description = "Report found", content = {
                    @Content(mediaType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
            }),
            @ApiResponse(responseCode = "404", description = "Report not found")
    })
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
    @Operation(description = "Retrieves information about all reports", summary = "Retrieves information about all reports", responses = {
            @ApiResponse(responseCode = "200", description = "Reports retrieved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = "array", example = "[{ \"filename\": \"report1\", \"url\": \"https://localhost:8080/report1\", \"fileSize\": 1000, \"uploadDate\": \"2022-01-01T00:00:00\" }, { \"filename\": \"report2\", \"url\": \"https://localhost:8080/report2\", \"fileSize\": 2000, \"uploadDate\": \"2022-01-01T00:00:00\" }]"))
            }),
            @ApiResponse(responseCode = "500", description = "Error getting all reports")
    })
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
    @Operation(description = "Creates a report for the specified time range", summary = "Creates a report for the specified time range", parameters = {
            @Parameter(name = "from", description = "The start time of the report in string format", required = true, in = ParameterIn.QUERY, example = "2023-01-01T00:00:00"),
            @Parameter(name = "to", description = "The end time of the report in string format", required = true, in = ParameterIn.QUERY, example = "2023-01-01T23:59:59")
    }, responses = {
            @ApiResponse(responseCode = "200", description = "Report created"),
            @ApiResponse(responseCode = "400", description = "Error parsing date"),
            @ApiResponse(responseCode = "500", description = "Error creating report")
    })
    @PostMapping("")
    public ResponseEntity<Object> createReport(@RequestBody Map<String, String> body) {
        try {

            LocalDateTime fromTime = LocalDateTime.parse(body.get("from"), DateTimeFormatter.ISO_DATE_TIME);
            LocalDateTime toTime = LocalDateTime.parse(body.get("to"), DateTimeFormatter.ISO_DATE_TIME);
            service.createReport(fromTime, toTime);
            return ResponseEntity.ok().build();

        } catch (DateTimeParseException e) {

            logger.error("Error parsing date", e);
            return ResponseEntity.badRequest().build();

        } catch (Exception e) {

            logger.error("Error creating report", e);
            return ResponseEntity.internalServerError().build();
            
        }
    }
}

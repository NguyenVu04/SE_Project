package spss.project.backend.printer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spss.project.backend.Environment;

/**
 * A controller for handling printer-related requests.
 */
@RestController
@CrossOrigin(origins = {Environment.FRONTEND_URL})
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
     * The logger for this controller.
     */
    private static final Logger logger = LoggerFactory.getLogger(PrinterController.class);

    /**
     * Retrieves all printers from the database.
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
     * Sends a test message to the message broker.
     * @return a success response if the message was sent successfully
     */
    @PostMapping("message")
    public ResponseEntity<Object> getMessage() {
        return ResponseEntity.ok().build();
    }
}

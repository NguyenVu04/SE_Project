package spss.project.backend.user.spso;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
import spss.project.backend.printer.PrinterService;

/**
 * Handles all requests related to SPSOs.
 */
@RestController
@CrossOrigin(origins = { Environment.FRONTEND_URL })
@RequestMapping("spso")
public class SPSOController {
    /**
     * This constructor is protected to prevent direct instantiation.
     */
    protected SPSOController() {
    }

    /**
     * The service for working with SPSOs.
     */
    @Autowired
    private SPSOService service;

    /**
     * The service for working with printers.
     */
    @Autowired
    private PrinterService printerService;

    /**
     * Logger for the class.
     */
    private static final Logger logger = LoggerFactory.getLogger(SPSOController.class);

    /**
     * Retrieves the SPSO id for a given email.
     * 
     * @param email the email of the SPSO to find the id for
     * @return a map containing the SPSO id, or null if no such SPSO exists
     * @throws Exception if an error occurs while retrieving the SPSO id
     */
    @GetMapping("id")
    public ResponseEntity<Object> getSPSOId(@RequestParam("email") String email) {
        try {
            Map<String, Object> response = new HashMap<>();
            String id = service.getSPSOId(email);
            response.put("id", id);

            return ResponseEntity.ok().body(response);

        } catch (Exception e) {

            logger.error("Error getting SPSO id", e);
            return ResponseEntity.notFound().build();

        }
    }

    /**
     * Retrieves a SPSO by its id.
     * 
     * @param id the id of the SPSO to retrieve
     * @return the SPSO with the given id, or null if no such SPSO exists
     * @throws Exception if an error occurs while retrieving the SPSO
     */
    @GetMapping("")
    public ResponseEntity<Object> getSPSO(@RequestParam("id") String id) {
        try {
            return ResponseEntity.ok().body(service.getSPSO(id));
        } catch (Exception e) {
            logger.error("Error getting SPSO", e);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("printer")
    public ResponseEntity<Object> addPrinter(@RequestBody Map<String, Object> body) {
        try {
            String url = (String) body.get("url");
            String model = (String) body.get("model");
            String description = (String) body.get("description");
            String campusName = (String) body.get("campusName");
            String buildingName = (String) body.get("buildingName");
            String roomNumber = (String) body.get("roomNumber");
            boolean active = (boolean) body.get("active");

            printerService.save(
                    url,
                    model,
                    description,
                    campusName,
                    buildingName,
                    roomNumber,
                    active);

            return ResponseEntity.ok().build();

        } catch (DuplicateKeyException e) {

            logger.error("Printer already exists", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        } catch (ClassCastException e) {

            logger.error("Error parsing printer data", e);
            return ResponseEntity.badRequest().build();

        } catch (Exception e) {

            logger.error("Error adding printer", e);
            return ResponseEntity.internalServerError().build();

        }
    }
}

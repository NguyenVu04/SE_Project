package spss.project.backend.configuration.system;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import spss.project.backend.Environment;

/**
 * Handles all requests related to the system configuration.
 */
@RestController
@CrossOrigin(origins = { Environment.FRONTEND_URL })
@RequestMapping("config")
public class SystemConfigController {
    @Autowired
    private SystemConfigService service;

    private static final Logger logger = LoggerFactory.getLogger(SystemConfigController.class);

    /**
     * The default constructor for the SystemConfigController.
     * This constructor is protected to prevent external instantiation.
     */
    protected SystemConfigController() {}

    /**
     * Returns the current system configuration.
     * 
     * @return the current system configuration
     */
    @GetMapping("")
    public ResponseEntity<Object> getCurrentConfig() {
        try {
            return ResponseEntity.ok()
                    .body(service.getCurrentSystemConfig());
        } catch (Exception e) {
            logger.error("Error getting current config", e);
        }

        return ResponseEntity.internalServerError()
                .build();
    }

    /**
     * Returns all saved system configurations.
     * 
     * @return a list of all saved system configurations
     */
    @GetMapping("all")
    public ResponseEntity<Object> getAllConfig() {
        try {
            return ResponseEntity.ok().body(service.getRepo().findAll());
        } catch (Exception e) {
            logger.error("Error getting all config", e);
        }

        return ResponseEntity.internalServerError().build();
    }

    /**
     * Saves the current system configuration.
     * @param body the request body containing the new system configuration
     * @return a success response if the configuration was saved successfully
     */
    @PostMapping("")
    public ResponseEntity<Object> saveCurrentConfig(@RequestBody Map<String, Object> body) {
        try {
            int paperSupplyDay = (int) body.get("paperSupplyDay");
            String createdBy = (String) body.get("createdBy");
            @SuppressWarnings("unchecked")
            List<String> fileTypes = (ArrayList<String>) body.get("fileTypes");

            service.saveCurrentSystemConfig(paperSupplyDay,
                    createdBy,
                    fileTypes);

            return ResponseEntity.ok()
                    .build();
        } catch (InvalidParameterException e) {

            logger.error("Invalid parameter", e);
            return ResponseEntity.badRequest()
                    .build();

        } catch (Exception e) {

            logger.error("Error saving current config", e);
            return ResponseEntity.internalServerError()
                .build();
                
        }
    }
}

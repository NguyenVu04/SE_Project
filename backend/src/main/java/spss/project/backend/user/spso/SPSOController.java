package spss.project.backend.user.spso;

import java.util.HashMap;
import java.util.Map;

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
 * Handles all requests related to SPSOs.
 */
@RestController
@CrossOrigin(origins = {Environment.FRONTEND_URL})
@RequestMapping("spso")
public class SPSOController {
    /**
     * The service for working with SPSOs.
     */
    @Autowired
    private SPSOService service;

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

        } catch(Exception e) {

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
        } catch(Exception e) {
            logger.error("Error getting SPSO", e);
            return ResponseEntity.notFound().build();
        }
    }
}


package spss.project.backend.configuration.system;

import java.security.InvalidParameterException;
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

@RestController
@CrossOrigin(origins = { Environment.FRONTEND_URL })
@RequestMapping("config")
public class SystemConfigController {
    @Autowired
    private SystemConfigService service;

    private static final Logger logger = LoggerFactory.getLogger(SystemConfigController.class);

    @GetMapping
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

    @GetMapping("all")
    public ResponseEntity<Object> getAllConfig() {
        try {
            return ResponseEntity.ok().body(service.getRepo().findAll());
        } catch (Exception e) {
            logger.error("Error getting all config", e);
        }

        return ResponseEntity.internalServerError().build();
    }

    @PostMapping
    public ResponseEntity<Object> saveCurrentConfig(@RequestBody Map<String, Object> body) {
        try {
            int paperSupplyDay = (int) body.get("paperSupplyDay");
            String createBy = (String) body.get("createBy");
            String cloudUrl = (String) body.get("cloudUrl");
            SystemConfig config = new SystemConfig(paperSupplyDay, createBy, cloudUrl);

            service.getRepo()
                    .save(config);

            return ResponseEntity.ok()
                    .build();
        } catch (InvalidParameterException e) {

            logger.error("Invalid parameter", e);
            return ResponseEntity.badRequest()
                    .build();

        } catch (Exception e) {

            logger.error("Error saving current config", e);

        }

        return ResponseEntity.internalServerError()
                .build();
    }
}

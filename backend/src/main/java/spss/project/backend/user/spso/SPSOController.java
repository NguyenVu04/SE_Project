package spss.project.backend.user.spso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
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
}

package spss.project.backend.user.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spss.project.backend.Environment;

/**
 * Handles all requests related to the system's administrators.
 */
@RestController
@CrossOrigin(origins = {Environment.FRONTEND_URL})
@RequestMapping("admin")
public class AdminController {

    /**
     * A service for working with admins.
     */
    @Autowired
    private AdminService service;

    /**
     * A logger for logging events.
     */
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    /**
     * Prevents the instantiation of this class.
     */
    protected AdminController() {}
}
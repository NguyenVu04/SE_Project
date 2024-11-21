package spss.project.backend.history.printing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spss.project.backend.Environment;

/**
 * Handles all requests related to the printing history of the students.
 */
@RestController
@CrossOrigin(origins = {Environment.FRONTEND_URL})
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
}

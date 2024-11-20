package spss.project.backend.history.printing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spss.project.backend.Environment;

@RestController
@CrossOrigin(origins = {Environment.FRONTEND_URL})
@RequestMapping("history/printing")
public class PrintingHistoryController {
    @Autowired
    private PrintingHistoryService service;

    private static final Logger logger = LoggerFactory.getLogger(PrintingHistoryController.class);
}

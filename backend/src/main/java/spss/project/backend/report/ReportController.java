package spss.project.backend.report;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spss.project.backend.Environment;

@RestController
@CrossOrigin(origins = {Environment.FRONTEND_URL})
@RequestMapping("/report")
public class ReportController {
    
}

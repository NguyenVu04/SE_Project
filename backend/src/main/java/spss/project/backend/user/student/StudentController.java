package spss.project.backend.user.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spss.project.backend.Environment;

/**
 * Handles all requests related to students.
 */
@RestController
@CrossOrigin(origins = {Environment.FRONTEND_URL})
@RequestMapping("student")
public class StudentController {

    /**
     * Service for handling student-related operations.
     */
    @Autowired
    private StudentService studentService;

    /**
     * Logger for logging StudentController events.
     */
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
}

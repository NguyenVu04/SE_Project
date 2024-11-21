package spss.project.backend.user.student;

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
 * Handles all requests related to students.
 */
@RestController
@CrossOrigin(origins = { Environment.FRONTEND_URL })
@RequestMapping("student")
public class StudentController {

    /**
     * Service for handling student-related operations.
     */
    @Autowired
    private StudentService service;

    /**
     * This constructor is declared protected to prevent instantiation
     * outside of this class.
     */
    protected StudentController() {}

    /**
     * Logger for logging StudentController events.
     */
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    /**
     * Retrieves a student by its id.
     * 
     * @param id the id of the student to retrieve
     * @return the student with the given id, or null if no such student exists
     * @throws Exception if an error occurs while retrieving the student
     */
    @GetMapping("")
    public ResponseEntity<Object> getStudent(@RequestParam("id") String id) {
        try {
            return ResponseEntity.ok()
                    .body(service.getStudent(id));
        } catch (Exception e) {
            logger.error("Error getting student", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Retrieves the student id for a given email.
     * 
     * @param email the email of the student to find the id for
     * @return a map containing the student id, or null if no such student exists
     * @throws Exception if an error occurs while retrieving the student id
     */
    @GetMapping("id")
    public ResponseEntity<Object> getStudentId(@RequestParam("email") String email) {
        try {

            Map<String, Object> response = new HashMap<>();
            Student student = service.getStudentByEmail(email);
            response.put("id", student.getId());

            return ResponseEntity.ok()
                    .body(response);

        } catch (Exception e) {
            logger.error("Error getting student id", e);
            return ResponseEntity.notFound().build();
        }
    }
}

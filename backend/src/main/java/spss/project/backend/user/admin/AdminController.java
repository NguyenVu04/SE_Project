package spss.project.backend.user.admin;

import java.time.LocalDate;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spss.project.backend.Environment;
import spss.project.backend.user.spso.SPSOService;
import spss.project.backend.user.student.StudentService;

/**
 * Handles all requests related to the system's administrators.
 */
@RestController
@CrossOrigin(origins = { Environment.FRONTEND_URL })
@RequestMapping("admin")
public class AdminController {

    /**
     * A service for working with admins.
     */
    @Autowired
    private AdminService service;

    /**
     * A service for working with students.
     */
    @Autowired
    private StudentService studentService;

    @Autowired
    private SPSOService spsoService;


    /**
     * A logger for logging events.
     */
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    /**
     * Prevents the instantiation of this class.
     */
    protected AdminController() {
    }

    /**
     * Retrieves an admin by its email.
     * 
     * @param email the email of the admin to retrieve
     * @return the admin with the given email, or null if no such admin exists
     */
    @GetMapping("")
    public ResponseEntity<Object> getAdmin(@RequestParam("email") String email) {
        try {

            Admin admin = service.getAdmin(email);
            return ResponseEntity.ok().body(admin);

        } catch (Exception e) {

            logger.error("Error getting admin", e);
            return ResponseEntity.notFound().build();

        }
    }

    /**
     * Adds an admin to the database.
     * 
     * @param body the request body containing the email, first name, and last name of the admin to add
     * @return a success response if the admin was added successfully
     */
    @PostMapping("")
    public ResponseEntity<Object> addAdmin(@RequestBody Map<String, Object> body) {
        try {

            String email = (String) body.get("email");
            String firstName = (String) body.get("firstName");
            String lastName = (String) body.get("lastName");
            service.save(email, firstName, lastName);
            return ResponseEntity.ok().build();

        } catch (Exception e) {

            logger.error("Error adding admin", e);
            return ResponseEntity.internalServerError().build();

        }
    }

    /**
     * Adds a student to the database.
     * 
     * @param body the request body containing the email, first name, last name, date of birth, and balance of the student to add
     * @return a success response if the student was added successfully
     */
    @PostMapping("student")
    public ResponseEntity<Object> addStudent(@RequestBody Map<String, Object> body) {

        try {

            String email = (String) body.get("email");
            String firstName = (String) body.get("firstName");
            String lastName = (String) body.get("lastName");
            LocalDate dateOfBirth = LocalDate.parse((String) body.get("dateOfBirth"));
            int balance = (int) body.get("balance");

            studentService.save(email, firstName, lastName, dateOfBirth, balance);
            return ResponseEntity.ok().build();

        }catch (DuplicateKeyException e) {

            logger.error("Student already exists", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        } catch (ClassCastException e) {

            logger.error("Error parsing student data", e);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {

            logger.error("Error adding student", e);
            return ResponseEntity.internalServerError().build();

        }
    }

    /**
     * Adds a SPSO to the database.
     * 
     * @param body the request body containing the email, first name, and last name of the SPSO to add
     * @return a success response if the SPSO was added successfully
     */
    @PostMapping("spso")
    public ResponseEntity<Object> addSPSO(@RequestBody Map<String, Object> body) {

        try {

            String email = (String) body.get("email");
            String firstName = (String) body.get("firstName");
            String lastName = (String) body.get("lastName");

            spsoService.save(email, firstName, lastName);
            return ResponseEntity.ok().build();

        } catch (DuplicateKeyException e) {

            logger.error("SPSO already exists", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        } catch (ClassCastException e) {

            logger.error("Error parsing SPSO data", e);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {

            logger.error("Error adding SPSO", e);
            return ResponseEntity.internalServerError().build();

        }
    }
}
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(description = "Retrieves an admin by their email", summary = "Get admin by email", parameters = @Parameter(name = "email", description = "The email of the admin to retrieve", required = true, in = ParameterIn.QUERY), responses = {
            @ApiResponse(description = "Admin retrieved successfully", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class))),
            @ApiResponse(description = "Admin not found", responseCode = "404"),
            @ApiResponse(description = "Error occurred while retrieving the admin", responseCode = "500")
    })
    @GetMapping("")
    public ResponseEntity<Object> getAdmin(@RequestParam("email") String email) {
        try {

            Admin admin = service.getAdmin(email);
            if (admin == null) {
                throw new NullPointerException();
            }
            return ResponseEntity.ok().body(admin);

        } catch (Exception e) {

            logger.error("Error getting admin", e);
            return ResponseEntity.notFound().build();

        }
    }

    /**
     * Adds an admin to the database.
     * 
     * @param body the request body containing the email, first name, and last name
     *             of the admin to add
     * @return a success response if the admin was added successfully
     */
    @Operation(description = "Add a new admin", summary = "Add admin", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the admin to be added", required = true, content = {
            @Content(examples = @ExampleObject(name = "admin sample", value = "{\n" +
                    "    \"email\": \"admin1@hcmut.edu.vn\",\n" +
                    "    \"firstName\": \"John\",\n" +
                    "    \"lastName\": \"Doe\"\n" +
                    "}"))
    }), responses = {
            @ApiResponse(responseCode = "200", description = "Admin added successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
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
     * @param body the request body containing the email, first name, last name,
     *             date of birth, and balance of the student to add
     * @return a success response if the student was added successfully
     */
    @Operation(description = "Adds a new student", summary = "Adds a new student", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the student to be added", required = true, content = @Content(
        examples = @ExampleObject(name = "student sample", value = "{\n" +
                "    \"email\": \"student1@hcmut.edu.vn\",\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Doe\",\n" +
                "    \"dateOfBirth\": \"2000-01-01\",\n" +
                "    \"balance\": 100\n" +
                "}")
    )), responses = {
            @ApiResponse(description = "Student added successfully", responseCode = "200"),
            @ApiResponse(description = "Student already exists", responseCode = "409"),
            @ApiResponse(description = "Invalid request data", responseCode = "400"),
            @ApiResponse(description = "Error occurred while adding student", responseCode = "500")
    })
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

        } catch (DuplicateKeyException e) {

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
     * Deletes a student from the database by its id.
     * 
     * @param id the id of the student to delete
     * @return a success response if the student was deleted successfully
     */
    @Operation(description = "Deletes a student by their ID", summary = "Deletes a student by their ID", parameters = @Parameter(name = "id", description = "The ID of the student to delete", required = true, in = ParameterIn.QUERY), responses = {
            @ApiResponse(description = "Student deleted successfully", responseCode = "200"),
            @ApiResponse(description = "Error occurred while deleting the student", responseCode = "500")
    })
    @DeleteMapping("student")
    public ResponseEntity<Object> deleteStudent(@RequestParam("id") String id) {

        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error deleting student", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Adds a SPSO to the database.
     * 
     * @param body the request body containing the email, first name, and last name
     *             of the SPSO to add
     * @return a success response if the SPSO was added successfully
     */
    @Operation(description = "Adds a new SPSO (Smart Printing Service Operator)",summary = "Adds a new SPSO (Smart Printing Service Operator)", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the SPSO to be added", required = true, content = @Content(examples = @ExampleObject(
            name = "SPSO sample",
            value = "{\n" +
                    "    \"email\": \"spso1@hcmut.edu.vn\",\n" +
                    "    \"firstName\": \"John\",\n" +
                    "    \"lastName\": \"Doe\"\n" +
                    "}"
    ))), responses = {
            @ApiResponse(description = "SPSO added successfully", responseCode = "200"),
            @ApiResponse(description = "SPSO already exists", responseCode = "409"),
            @ApiResponse(description = "Invalid request data", responseCode = "400"),
            @ApiResponse(description = "Error occurred while adding SPSO", responseCode = "500")
    })
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

    /**
     * Deletes a SPSO from the database by its id.
     * 
     * @param id the id of the SPSO to delete
     * @return a success response if the SPSO was deleted successfully
     */
    @Operation(description = "Deletes an SPSO by their ID", summary = "Deletes an SPSO by their ID", parameters = @Parameter(name = "id", description = "The ID of the SPSO to delete", required = true, in = ParameterIn.QUERY), responses = {
            @ApiResponse(description = "SPSO deleted successfully", responseCode = "200"),
            @ApiResponse(description = "Error occurred while deleting the SPSO", responseCode = "500")
    })
    @DeleteMapping("spso")
    public ResponseEntity<Object> deleteSPSO(@RequestParam("id") String id) {
        try {
            spsoService.deleteSPSO(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error deleting SPSO", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
package spss.project.backend.user.spso;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.StringToClassMapItem;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import spss.project.backend.Environment;
import spss.project.backend.printer.PrinterService;

/**
 * Handles all requests related to SPSOs.
 */
@RestController
@CrossOrigin(origins = { Environment.FRONTEND_URL })
@RequestMapping("spso")
public class SPSOController {
    /**
     * This constructor is protected to prevent direct instantiation.
     */
    protected SPSOController() {
    }

    /**
     * The service for working with SPSOs.
     */
    @Autowired
    private SPSOService service;

    /**
     * The service for working with printers.
     */
    @Autowired
    private PrinterService printerService;

    /**
     * Logger for the class.
     */
    private static final Logger logger = LoggerFactory.getLogger(SPSOController.class);

    /**
     * Retrieves the SPSO id for a given email.
     * 
     * @param email the email of the SPSO to find the id for
     * @return a map containing the SPSO id, or null if no such SPSO exists
     */
    @Operation(summary = "Get SPSO ID by email", description = "Retrieves the SPSO id for a given email", parameters = {
            @Parameter(name = "email", description = "The email of the SPSO to find the id for", required = true, in = ParameterIn.QUERY)
    }, responses = {
            @ApiResponse(responseCode = "200", description = "SPSO ID retrieved successfully", content = @Content(schema = @Schema(type = "object", properties = {
                    @StringToClassMapItem(key = "id", value = String.class)
            }))),
            @ApiResponse(responseCode = "404", description = "SPSO not found")
    })
    @GetMapping("id")
    public ResponseEntity<Object> getSPSOId(@RequestParam("email") String email) {
        try {
            Map<String, Object> response = new HashMap<>();
            String id = service.getSPSOId(email);
            response.put("id", id);

            return ResponseEntity.ok().body(response);

        } catch (Exception e) {

            logger.error("Error getting SPSO id", e);
            return ResponseEntity.notFound().build();

        }
    }

    /**
     * Retrieves a SPSO by its id.
     * 
     * @param id the id of the SPSO to retrieve
     * @return the SPSO with the given id, or null if no such SPSO exists
     */
    @Operation(description = "Get SPSO by ID", summary = "Get SPSO by ID", parameters = {
            @Parameter(name = "id", description = "The ID of the SPSO to retrieve", required = true, in = ParameterIn.QUERY)
    }, responses = {
            @ApiResponse(responseCode = "200", description = "SPSO retrieved successfully", content = @Content(schema = @Schema(implementation = SPSO.class))),
            @ApiResponse(responseCode = "404", description = "SPSO not found")
    })
    @GetMapping("")
    public ResponseEntity<Object> getSPSO(@RequestParam("id") String id) {
        try {
            return ResponseEntity.ok().body(service.getSPSO(id));
        } catch (Exception e) {
            logger.error("Error getting SPSO", e);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Adds a printer to the database.
     * 
     * @param body the request body containing the url, model, description,
     *             campus name, building name, room number, and active status of the
     *             printer
     * @return a success response if the printer was added successfully
     */
    @Operation(description = "Add a new printer", summary = "Add a new printer")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Details of the printer to be added", required = true, content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "printer sample", 
    value = "{\n" +
            "    \"model\": \"Printer Model\",\n" +
            "    \"description\": \"Printer Description\",\n" +
            "    \"campusName\": \"Campus Name\",\n" +
            "    \"buildingName\": \"Building Name\",\n" +
            "    \"roomNumber\": \"Room Number\",\n" +
            "    \"active\": true\n" +
            "}")))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Printer added successfully"),
            @ApiResponse(responseCode = "409", description = "Printer already exists"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("printer")
    public ResponseEntity<Object> addPrinter(@RequestBody Map<String, Object> body) {
        try {
            String model = (String) body.get("model");
            String description = (String) body.get("description");
            String campusName = (String) body.get("campusName");
            String buildingName = (String) body.get("buildingName");
            String roomNumber = (String) body.get("roomNumber");
            boolean active = (boolean) body.get("active");

            printerService.save(
                    model,
                    description,
                    campusName,
                    buildingName,
                    roomNumber,
                    active);

            return ResponseEntity.ok().build();

        } catch (DuplicateKeyException e) {

            logger.error("Printer already exists", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        } catch (ClassCastException e) {

            logger.error("Error parsing printer data", e);
            return ResponseEntity.badRequest().build();

        } catch (Exception e) {

            logger.error("Error adding printer", e);
            return ResponseEntity.internalServerError().build();

        }
    }

    /**
     * Updates the status of a printer with the given id.
     * 
     * @param id     the id of the printer to update
     * @param status the new status of the printer, either "active" or "inactive"
     * @return a response entity indicating the outcome of the operation
     */
    @Operation(description = "Set printer status", summary = "Set printer status", parameters = {
            @Parameter(name = "id", description = "The ID of the printer to update", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "status", description = "The new status of the printer: \"active\" or \"inactive\"", required = true, in = ParameterIn.QUERY)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Printer status updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Printer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("printer")
    public ResponseEntity<Object> setPrinterStatus(
            @RequestParam("id") String id,
            @RequestParam("status") String status) {
        try {

            if (status.equals("active")) {

                printerService.activatePrinter(id);

            } else if (status.equals("inactive")) {

                printerService.deactivatePrinter(id);

            } else {

                logger.error("Invalid status", new InvalidParameterException());
                return ResponseEntity.badRequest().build();

            }

            return ResponseEntity.ok().build();

        } catch (NotFoundException e) {

            logger.error("Printer not found", e);
            return ResponseEntity.notFound().build();

        } catch (Exception e) {

            logger.error("Error activating printer", e);
            return ResponseEntity.internalServerError().build();

        }
    }
}

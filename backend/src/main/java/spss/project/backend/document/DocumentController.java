package spss.project.backend.document;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.gridfs.model.GridFSFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import spss.project.backend.configuration.system.SystemConfig;
import spss.project.backend.configuration.system.SystemConfigService;

/**
 * Handles all requests related to documents. This controller provides methods
 * for saving, retrieving, and deleting documents from the database.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("document")
public class DocumentController {
    /**
     * Default constructor for DocumentController.
     * This constructor is protected to prevent direct instantiation.
     */
    protected DocumentController() {
    }

    /**
     * Service class for working with documents. This service provides methods for
     * saving, retrieving, and deleting documents from the database.
     */
    @Autowired
    private DocumentService service;

    /**
     * Service class for managing system configurations. This service provides
     * methods
     * for saving and retrieving system configurations from the database.
     */
    @Autowired
    private SystemConfigService systemConfigService;

    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    /**
     * Retrieves a document for a given student ID and file name.
     * 
     * @param studentId the ID of the student
     * @param filename  the name of the document
     * @return the document as a byte array wrapped in a ResponseEntity
     */
    @Operation(description = "Retrieves a document for a given student ID and file name", summary = "Retrieves a document for a given student ID and file name", parameters = {
            @Parameter(name = "studentId", description = "The ID of the student", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "filename", description = "The name of the document", required = true, in = ParameterIn.QUERY)
    }, responses = {
            @ApiResponse(responseCode = "200", description = "Document found", content = {
                    @Content(mediaType = "application/octet-stream")
            }),
            @ApiResponse(responseCode = "404", description = "Document not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("")
    public ResponseEntity<Object> getDocument(
            @RequestParam("studentId") String studentId,
            @RequestParam("filename") String filename) {
        try {
            byte[] document = service.getDocument(studentId, filename);
            return ResponseEntity.ok()
                    .headers(headers -> headers.add(
                            "Content-Disposition",
                            "attachment; filename=\"" + filename + "\""))
                    .body(document);
        } catch (NotFoundException e) {
            logger.error("Document not found", e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Error getting document", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Retrieves all documents for a given student ID.
     * 
     * @param studentId the ID of the student
     * @return a list of documents wrapped in a ResponseEntity
     */
    @Operation(description = "Retrieves all documents for a given student ID", summary = "Retrieves all documents for a given student ID", parameters = {
            @Parameter(name = "studentId", description = "The ID of the student", required = true, in = ParameterIn.QUERY)
    }, responses = {
            @ApiResponse(responseCode = "200", description = "Documents found", content = {
                    @Content(mediaType = "application/json", 
                    examples = @ExampleObject(value = "[\n{\"fileName\": \"document.pdf\", \"url\": \"https://example.com/document.pdf\", \"fileSize\": 12345, \"uploadDate\": \"2021-01-01T00:00:00Z\"}\n]"))
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("all")
    public ResponseEntity<Object> getAllDocuments(@RequestParam("studentId") String studentId) {
        try {
            MongoCursor<GridFSFile> files = service.getDocuments(studentId);
            List<Map<String, Object>> documents = new ArrayList<>();
            
            while (files.hasNext()) {
                GridFSFile file = files.next();
                String fileName = service.extractFileName(file.getFilename());
                Map<String, Object> document = Map.of(
                        "fileName", fileName,
                        "url", service.getDocumentUrl(studentId, fileName),
                        "fileSize", file.getLength(),
                        "uploadDate", file.getUploadDate());
                documents.add(document);
            }

            return ResponseEntity.ok().body(documents);

        } catch (Exception e) {
            logger.error("Error getting all documents", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Saves a document for a given student ID.
     * 
     * @param studentId the ID of the student
     * @param file      the document to be saved
     * @return a success or error response wrapped in a ResponseEntity
     */
    @Operation(description = "Saves a document for a given student ID", summary = "Saves a document for a given student ID", requestBody = @RequestBody(description = "Document to be saved", required = true, content = @Content(mediaType = "multipart/form-data", schema = @Schema(example = "{\"studentId\": \"123456\", \"file\": \"file\"}"))), responses = {
            @ApiResponse(responseCode = "200", description = "Document saved"),
            @ApiResponse(responseCode = "409", description = "Document already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("")
    public ResponseEntity<Object> saveDocument(
            @RequestPart("studentId") String studentId,
            @RequestPart("file") MultipartFile file) {

        try {
            SystemConfig config = systemConfigService.getCurrentSystemConfig();
            if (!config.hasFileType(file.getContentType())) {
                logger.error("File type not allowed: " + file.getContentType(), new InvalidParameterException());
                return ResponseEntity.badRequest().build();
            }

            service.saveDocument(file, studentId);
            return ResponseEntity.ok().build();

        } catch (DuplicateKeyException e) {

            logger.error("Document already exists", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        } catch (Exception e) {
            logger.error("Error saving document", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

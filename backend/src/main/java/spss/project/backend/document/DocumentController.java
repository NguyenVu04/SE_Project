package spss.project.backend.document;

import java.nio.channels.AlreadyBoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

@RestController
@CrossOrigin("*")
@RequestMapping("document")
public class DocumentController {
    @Autowired
    private DocumentService service;

    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @GetMapping("")
    public ResponseEntity<Object> getDocument(
            @RequestParam("studentId") String studentId,
            @RequestParam("fileName") String fileName) {
        try {

            byte[] document = service.getDocument(studentId, fileName);
            return ResponseEntity.ok()
                    .headers(headers -> headers.add(
                            "Content-Disposition",
                            "attachment; filename=\"" + fileName + "\""))
                    .body(document);

        } catch (NotFoundException e) {

            logger.error("Document not found", e);
            return ResponseEntity.notFound().build();

        } catch (Exception e) {

            logger.error("Error getting document", e);
            return ResponseEntity.internalServerError().build();

        }
    }

    @GetMapping("all")
    public ResponseEntity<Object> getAllDocuments(@RequestParam("studentId") String studentId) {
        try {
            MongoCursor<GridFSFile> files = service.getDocuments(studentId);

            List<Map<String, Object>> documents = new ArrayList<>();

            while (files.hasNext()) {
                GridFSFile file = files.next();
                Map<String, Object> document = Map.of(
                        "fileName", service.extractFileName(file.getFilename()),
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

    @PostMapping("")
    public ResponseEntity<Object> saveDocument(
            @RequestPart("studentId") String studentId,
            @RequestPart("file") MultipartFile file) {
        
        try {

            service.saveDocument(file, studentId);
            return ResponseEntity.ok().build();

        } catch (AlreadyBoundException e) {

            logger.error("Document already bound", e);
            return ResponseEntity.badRequest().build();

        } catch (Exception e) {

            logger.error("Error saving document", e);
            return ResponseEntity.internalServerError().build();

        }
    }
}

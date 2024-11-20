package spss.project.backend.document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import spss.project.backend.Environment;

@Service
public class DocumentService {
    @Autowired
    private GridFsOperations operations;

    /**
     * Saves a document to the database, given a MultipartFile and a student ID.
     * 
     * @param file      the document to be saved
     * @param studentId the ID of the student to save the document for
     * @throws Exception if the document cannot be saved
     */
    public void saveDocument(MultipartFile file, String studentId) throws Exception {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("uploadDate", new Date());
        metadata.put("fileSize", file.getSize());
        metadata.put("author", studentId);

        operations.store(file.getInputStream(),
                this.convertToGridFsFileName(studentId, studentId),
                file.getContentType(),
                metadata);
    }

    /**
     * Deletes a document from the database, given a student ID and the name of the
     * document.
     *
     * @param studentId the ID of the student
     * @param fileName  the name of the document
     * @throws Exception if something goes wrong
     */
    public void deleteDocument(String studentId, String fileName) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where(fileName)
                .is(this.convertToGridFsFileName(fileName, studentId)));
        operations.delete(query);
    }

    /**
     * Retrieves a document from the database, given a student ID and the name of
     * the document.
     *
     * @param studentId the ID of the student
     * @param fileName  the name of the document
     * @return the document as a byte array
     * @throws Exception if the document cannot be found
     */
    public byte[] getDocument(String studentId, String fileName) throws Exception {
        return operations.getResource(this.convertToGridFsFileName(fileName, studentId))
                .getInputStream()
                .readAllBytes();
    }

    /**
     * Converts a file name and student ID into a combined file path.
     *
     * @param fileName  the name of the file
     * @param studentId the ID of the student
     * @return a string representing the file path in the format
     *         "studentId/fileName"
     */
    public String convertToGridFsFileName(String fileName, String studentId) {
        return studentId + Environment.FILE_SEPERATOR + fileName;
    }

    /**
     * Extracts the name of the file from a combined file path.
     * 
     * @param gridFsFileName the combined file path
     * @return the name of the file
     */
    public String extractFileName(String gridFsFileName) {
        return gridFsFileName.split(Environment.FILE_SEPERATOR)[1];
    }

    /**
     * Extracts the student ID from a combined file path.
     *
     * @param gridFsFileName the combined file path in the format "studentId/fileName"
     * @return the student ID
     */
    public String extractStudentId(String gridFsFileName) {
        return gridFsFileName.split(Environment.FILE_SEPERATOR)[0];
    }
}

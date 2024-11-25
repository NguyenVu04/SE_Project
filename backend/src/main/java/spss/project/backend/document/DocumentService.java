package spss.project.backend.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.gridfs.model.GridFSFile;

import spss.project.backend.Environment;

/**
 * A service for working with documents. This service provides methods for
 * saving, retrieving, and deleting documents from the database.
 */
@Service
public class DocumentService {
    /**
     * This is the constructor of the DocumentService class. It is protected to
     * prevent instantiation from outside the package.
     */
    protected DocumentService() {}
    /**
     * An instance of GridFsOperations, which provides methods for saving and
     * retrieving documents from the database.
     */
    @Autowired
    private GridFsOperations operations;

    /**
     * Saves a document to the database, given a MultipartFile and a student ID.
     * 
     * @param file      the document to be saved
     * @param studentId the ID of the student to save the document for
     * @throws Exception if the document cannot be saved
     */
    public void saveDocument(MultipartFile file, String studentId) throws DuplicateKeyException, Exception {
        String gridFsFileName = this.convertToGridFsFileName(
                file.getOriginalFilename(),
                studentId);

        GridFsResource resource = operations.getResource(gridFsFileName);

        if (resource.exists()) {
            throw new DuplicateKeyException(gridFsFileName);
        }

        operations.store(file.getInputStream(),
                gridFsFileName,
                file.getContentType());
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
     * @throws NotFoundException if the document cannot be found
     * @throws Exception if the document cannot be found
     */
    public byte[] getDocument(String studentId, String fileName) throws NotFoundException, Exception {
        GridFsResource resource = operations.getResource(this.convertToGridFsFileName(fileName, studentId));

        if (!resource.exists()) {
            throw new NotFoundException();
        }

        return resource.getInputStream()
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
     * @param gridFsFileName the combined file path in the format
     *                       "studentId/fileName"
     * @return the student ID
     */
    public String extractStudentId(String gridFsFileName) {
        return gridFsFileName.split(Environment.FILE_SEPERATOR)[0];
    }

    /**
     * Finds all documents for the given student ID.
     *
     * @param studentId the ID of the student
     * @return a cursor over the documents
     * @throws Exception if something goes wrong
     */
    public MongoCursor<GridFSFile> getDocuments(String studentId) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("filename").regex("^" + studentId + Environment.FILE_SEPERATOR));

        MongoCursor<GridFSFile> iterator = operations.find(query).cursor();

        return iterator;
    }

    /**
     * Gets the GridFS operations object.
     * 
     * @return the GridFS operations object
     */
    public GridFsOperations getOperations() {
        return operations;
    }
}

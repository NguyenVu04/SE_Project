package spss.project.backend.configuration.system;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * This class represents a configuration for the system. The configuration is
 * stored in the database and contains information about the system such as the
 * default paper supply date, the file types that are allowed and the user who
 * created the configuration.
 */
@Document(collection = "SystemConfig")
public class SystemConfig {
    @MongoId
    private String _id;
    /**
     * The default paper supply date. This is the date that the system will use
     * to determine the date to print the paper supply reminder.
     */
    private int _paperSupplyDay;
    /**
     * A list of file types that are allowed to be uploaded to the system. This
     * list is used to validate the file type of documents that are uploaded.
     */
    private List<String> _fileTypes;
    /**
     * The date that the configuration was created. This is the date that the
     * configuration was added to the database.
     */
    private Date _createdAt;
    /**
     * The user who created the configuration. This is the user who added the
     * configuration to the database.
     */
    private String _createdBy;
    /**
     * The URL to the cloud service that store files.
     */
    private String _cloudUrl;

    /**
     * Creates a new configuration with the given paper supply date and the
     * given user who created the configuration.
     * 
     * @param paperSupplyDay the paper supply date
     * @param createdBy       the user who created the configuration
     * @param cloudUrl       the URL to the cloud service that store files
     * @param fileTypes      the list of allowed file types
     * @throws InvalidParameterException if the paper supply date is outside the
     *                                   range of 1 to 28
     * @throws Exception if the paper supply date is outside the range of 1
     *                   to 28
     */
    public SystemConfig(
        int _paperSupplyDay, 
        String _createdBy, 
        String _cloudUrl, 
        List<String> _fileTypes) throws InvalidParameterException, Exception {
        if (_paperSupplyDay < 1 || _paperSupplyDay > 28) {
            throw new InvalidParameterException("Paper supply day must be between 1 and 28.");
        }

        this._paperSupplyDay = _paperSupplyDay;
        this._fileTypes = _fileTypes;
        this._createdAt = new Date();
        this._createdBy = _createdBy;
        this._cloudUrl = _cloudUrl;
    }

    /**
     * Gets the id of the configuration.
     * 
     * @return the id of the configuration
     */
    public String getId() {
        return _id;
    }

    /**
     * Gets the paper supply date of the configuration.
     * 
     * @return the paper supply date of the configuration
     */
    public int getPaperSupplyDate() {
        return _paperSupplyDay;
    }

    /**
     * Sets the paper supply date of the configuration.
     * 
     * @param paperSupplyDate the new paper supply date
     * @throws Exception if the paper supply date is outside the range of 1
     *                   to 28
     */
    public void setPaperSupplyDate(int paperSupplyDate) throws Exception {
        if (paperSupplyDate < 0 || paperSupplyDate > 28) {
            throw new Exception("Paper supply date must be between 0 and 28.");
        }

        this._paperSupplyDay = paperSupplyDate;
    }

    /**
     * Adds a file type to the list of allowed file types.
     * 
     * @param fileType the file type to add
     */
    public void addFileType(String fileType) {
        if (this._fileTypes.contains(fileType)) {
            return;
        }

        this._fileTypes.add(fileType);
    }

    /**
     * Checks if the given file type is in the list of allowed file types.
     * 
     * @param fileType the file type to check
     * @return true if the file type is in the list of allowed file types, false
     *         otherwise
     */
    public boolean hasFileType(String fileType) {
        return this._fileTypes.contains(fileType);
    }

    /**
     * Removes a file type from the list of allowed file types.
     * 
     * @param fileType the file type to remove
     * @return true if the file type was removed, false otherwise
     */
    public boolean removeFileType(String fileType) {
        return this._fileTypes.remove(fileType);
    }

    /**
     * Gets the list of allowed file types.
     * 
     * @return the list of allowed file types
     */
    public List<String> getFileTypes() {
        return Collections.unmodifiableList(this._fileTypes);
    }

    /**
     * Sets the list of allowed file types.
     * 
     * @param fileTypes the list of allowed file types
     */
    public void setFileTypes(List<String> fileTypes) {
        this._fileTypes = new ArrayList<>(fileTypes);
    }

    /**
     * Gets the user who created the configuration.
     * 
     * @return the user who created the configuration
     */
    public String getCreatedBy() {
        return _createdBy;
    }

    /**
     * Gets the date that the configuration was created.
     * 
     * @return the date that the configuration was created
     */
    public Date getCreatedAt() {
        return _createdAt;
    }

    /**
     * Gets the URL of the cloud storage service.
     * 
     * @return the URL of the cloud storage service
     */
    public String getCloudUrl() {
        return _cloudUrl;
    }

    /**
     * Sets the URL of the cloud storage service.
     * 
     * @param cloudUrl the URL to set for the cloud storage service
     */
    public void setCloudUrl(String cloudUrl) {
        this._cloudUrl = cloudUrl;
    }
}
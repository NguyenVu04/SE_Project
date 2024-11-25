package spss.project.backend.configuration.system;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
    private String id;

    /**
     * The default number of pages that will be printed if no number of pages
     * is specified. This is used to validate the number of pages that are
     * specified in a printing job.
     */
    private int defaultNumberOfPages;

    /**
     * The default paper supply date. This is the date that the system will use
     * to determine the date to print the paper supply reminder.
     */
    private int paperSupplyDay;
    /**
     * A list of file types that are allowed to be uploaded to the system. This
     * list is used to validate the file type of documents that are uploaded.
     */
    private List<String> fileTypes;
    /**
     * The date that the configuration was created. This is the date that the
     * configuration was added to the database.
     */
    private LocalDateTime createdAt;
    /**
     * The user who created the configuration. This is the user who added the
     * configuration to the database.
     */
    private String createdBy;

    /**
     * Creates a new configuration with the given paper supply date and the
     * given user who created the configuration.
     * 
     * @param defaultNumberOfPages the default number of pages
     * @param paperSupplyDay the paper supply date
     * @param createdBy       the user who created the configuration
     * @param fileTypes      the list of allowed file types
     * @throws InvalidParameterException if the paper supply date is outside the
     *                                   range of 1 to 28
     * @throws Exception if the paper supply date is outside the range of 1
     *                   to 28
     */
    public SystemConfig(
        int defaultNumberOfPages,
        int paperSupplyDay, 
        String createdBy, 
        List<String> fileTypes) throws InvalidParameterException, Exception {
        if (paperSupplyDay < 1 || paperSupplyDay > 28) {
            throw new InvalidParameterException("Paper supply day must be between 1 and 28.");
        }
        this.defaultNumberOfPages = defaultNumberOfPages;
        this.paperSupplyDay = paperSupplyDay;
        this.fileTypes = fileTypes;
        this.createdAt = LocalDateTime.now();
        this.createdBy = createdBy;
    }

    /**
     * Gets the id of the configuration.
     * 
     * @return the id of the configuration
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the default number of pages of the configuration.
     * 
     * @return the default number of pages of the configuration
     */
    public int getDefaultNumberOfPages() {
        return defaultNumberOfPages;
    }

    /**
     * Sets the default number of pages of the configuration.
     * 
     * @param defaultNumberOfPages the new default number of pages
     */
    public void setDefaultNumberOfPages(int defaultNumberOfPages) {
        this.defaultNumberOfPages = defaultNumberOfPages;
    }

    /**
     * Gets the paper supply date of the configuration.
     * 
     * @return the paper supply date of the configuration
     */
    public int getPaperSupplyDay() {
        return paperSupplyDay;
    }

    /**
     * Sets the paper supply date of the configuration.
     * 
     * @param paperSupplyDay the new paper supply date
     * @throws Exception if the paper supply date is outside the range of 1
     *                   to 28
     */
    public void setPaperSupplyDay(int paperSupplyDay) throws Exception {
        if (paperSupplyDay < 0 || paperSupplyDay > 28) {
            throw new Exception("Paper supply date must be between 0 and 28.");
        }

        this.paperSupplyDay = paperSupplyDay;
    }

    /**
     * Adds a file type to the list of allowed file types.
     * 
     * @param fileType the file type to add
     */
    public void addFileType(String fileType) {
        if (this.fileTypes.contains(fileType)) {
            return;
        }

        this.fileTypes.add(fileType);
    }

    /**
     * Checks if the given file type is in the list of allowed file types.
     * 
     * @param fileType the file type to check
     * @return true if the file type is in the list of allowed file types, false
     *         otherwise
     */
    public boolean hasFileType(String fileType) {
        return this.fileTypes.contains(fileType);
    }

    /**
     * Removes a file type from the list of allowed file types.
     * 
     * @param fileType the file type to remove
     * @return true if the file type was removed, false otherwise
     */
    public boolean removeFileType(String fileType) {
        return this.fileTypes.remove(fileType);
    }

    /**
     * Gets the list of allowed file types.
     * 
     * @return the list of allowed file types
     */
    public List<String> getFileTypes() {
        return Collections.unmodifiableList(this.fileTypes);
    }

    /**
     * Sets the list of allowed file types.
     * 
     * @param fileTypes the list of allowed file types
     */
    public void setFileTypes(List<String> fileTypes) {
        this.fileTypes = new ArrayList<>(fileTypes);
    }

    /**
     * Gets the user who created the configuration.
     * 
     * @return the user who created the configuration
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Gets the date that the configuration was created.
     * 
     * @return the date that the configuration was created
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
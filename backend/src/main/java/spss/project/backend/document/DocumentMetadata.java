package spss.project.backend.document;

import java.util.Date;

/**
 * Represents metadata for a document.
 */
public class DocumentMetadata {
    /**
     * The date the document was uploaded.
     */
    private Date uploadDate;

    /**
     * The size of the document in bytes.
     */
    private long fileSize;

    /**
     * Constructs a DocumentMetadata object with the specified upload date and file size.
     *
     * @param uploadDate the date the document was uploaded
     * @param fileSize   the size of the document in bytes
     */
    public DocumentMetadata(Date uploadDate, long fileSize) {
        this.uploadDate = uploadDate;
        this.fileSize = fileSize;
    }

    /**
     * Returns the upload date of the document.
     *
     * @return the upload date
     */
    public Date getUploadDate() {
        return uploadDate;
    }

    /**
     * Returns the file size of the document in bytes.
     *
     * @return the file size
     */
    public long getFileSize() {
        return fileSize;
    }
}

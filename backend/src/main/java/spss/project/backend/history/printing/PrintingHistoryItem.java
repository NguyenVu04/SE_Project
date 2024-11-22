package spss.project.backend.history.printing;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * A class representing a printing history item.
 */
@Document(collection = "PrintingHistory")
public class PrintingHistoryItem {
    /**
     * The ID of the printing history item.
     */
    @MongoId
    private String _id;

    /**
     * The ID of the student who requested the printing job.
     */
    private String _studentId;

    /**
     * The ID of the printer which the document was printed on.
     */
    private String _printerId;

    /**
     * The ID of the document which was printed.
     */
    private String _documentId;

    /**
     * The size of the paper which the document was printed on.
     */
    private String _paperSize;

    /**
     * The page numbers of the document which were printed.
     */
    private List<Integer> _pageNumbers;

    /**
     * The number of copies of the document which were printed.
     */
    private int _numberOfCopies;

    /**
     * Whether the document was printed single or double sided.
     */
    private boolean _singleSided;

    /**
     * The time at which the printing job was requested.
     */
    private LocalDateTime _timeOrdered;

    /**
     * The time at which the printing job was completed.
     */
    private LocalDateTime _timePrinted;

    /**
     * Whether the printing job was successful or not.
     */
    private boolean _successful;

    /**
     * Creates a new printing history item.
     * 
     * @param _studentId      the ID of the student who requested the printing job
     * @param _printerId      the ID of the printer which the document was printed on
     * @param _documentId     the ID of the document which was printed
     * @param _paperSize      the size of the paper which the document was printed on
     * @param _pageNumbers    the page numbers of the document which were printed
     * @param _numberOfCopies the number of copies of the document which were printed
     * @param _singleSided    whether the document was printed single or double sided
     * @param _timeOrdered    the time at which the printing job was requested
     * @param _timePrinted    the time at which the printing job was completed
     * @param _successful     whether the printing job was successful or not
     */
    public PrintingHistoryItem(
            String _studentId,
            String _printerId,
            String _documentId,
            String _paperSize,
            List<Integer> _pageNumbers,
            int _numberOfCopies,
            boolean _singleSided,
            LocalDateTime _timeOrdered,
            LocalDateTime _timePrinted,
            boolean _successful) {

        this._studentId = _studentId;
        this._printerId = _printerId;
        this._documentId = _documentId;
        this._paperSize = _paperSize;
        this._pageNumbers = _pageNumbers;
        this._numberOfCopies = _numberOfCopies;
        this._singleSided = _singleSided;
        this._timeOrdered = _timeOrdered;
        this._timePrinted = _timePrinted;
        this._successful = _successful;
    }

    /**
     * Gets the ID of the printing history item.
     * 
     * @return the ID of the printing history item
     */
    public String getId() {
        return _id;
    }

    /**
     * Gets the ID of the student who requested the printing job.
     * 
     * @return the ID of the student who requested the printing job
     */
    public String getStudentId() {
        return _studentId;
    }

    /**
     * Gets the ID of the printer which the document was printed on.
     * 
     * @return the ID of the printer which the document was printed on
     */
    public String getPrinterId() {
        return _printerId;
    }

    /**
     * Gets the ID of the document which was printed.
     * 
     * @return the ID of the document which was printed
     */
    public String getDocumentId() {
        return _documentId;
    }

    /**
     * Gets the size of the paper which the document was printed on.
     * 
     * @return the size of the paper which the document was printed on
     */
    public String getPaperSize() {
        return _paperSize;
    }

    /**
     * Gets the page numbers of the document which were printed.
     * 
     * @return the page numbers of the document which were printed
     */
    public List<Integer> getPageNumbers() {
        return List.copyOf(this._pageNumbers);
    }

    /**
     * Gets the number of copies of the document which were printed.
     * 
     * @return the number of copies of the document which were printed
     */
    public int getNumberOfCopies() {
        return _numberOfCopies;
    }

    /**
     * Gets the time at which the printing job was requested.
     * 
     * @return the time at which the printing job was requested
     */
    public LocalDateTime getTimeOrdered() {
        return _timeOrdered;
    }

    /**
     * Gets the time at which the printing job was completed.
     * 
     * @return the time at which the printing job was completed
     */
    public LocalDateTime getTimePrinted() {
        return _timePrinted;
    }

    /**
     * Checks if the printing job was single sided.
     * 
     * @return true if the printing job was single sided, false if it was double
     *         sided
     */
    public boolean isSingleSided() {
        return _singleSided;
    }

    /**
     * Checks if the printing job was successful.
     * 
     * @return true if the printing job was successful, false if it was not
     */
    public boolean isSuccessful() {
        return _successful;
    }
}

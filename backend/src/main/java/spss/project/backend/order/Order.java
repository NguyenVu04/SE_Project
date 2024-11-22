package spss.project.backend.order;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import spss.project.backend.document.PaperSize;

/**
 * A class representing an order from a student to print a document.
 */
@Document(collection = "Orders")
public class Order {
    /**
     * The ID of the order.
     */
    @MongoId
    private String _id;

    /**
     * The ID of the student who submitted the order.
     */
    private String _studentId;

    /**
     * The ID of the printer on which the document was printed.
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
     * The time at which the order was submitted.
     */
    private LocalDateTime _timeOrdered;

    /**
     * Whether the order was completed successfully.
     */
    private boolean _done;

    /**
     * Creates a new order.
     *
     * @param _studentId      the ID of the student who submitted the order
     * @param _printerId      the ID of the printer on which the document was printed
     * @param _documentId     the ID of the document which was printed
     * @param _paperSize      the size of the paper which the document was printed on
     * @param _pageNumbers    the page numbers of the document which were printed
     * @param _numberOfCopies the number of copies of the document which were printed
     * @param _singleSided    whether the document was printed single or double sided
     * @param _timeOrdered    the time at which the order was submitted
     * @param _done           whether the order was completed successfully
     */
    public Order(
            String _studentId,
            String _printerId,
            String _documentId,
            String _paperSize,
            List<Integer> _pageNumbers,
            int _numberOfCopies,
            boolean _singleSided,
            LocalDateTime _timeOrdered,
            boolean _done) {

        this._studentId = _studentId;
        this._printerId = _printerId;
        this._documentId = _documentId;
        this._paperSize = _paperSize;
        this._pageNumbers = _pageNumbers;
        this._numberOfCopies = _numberOfCopies;
        this._singleSided = _singleSided;
        this._timeOrdered = _timeOrdered;
        this._done = _done;
    }

    /**
     * Gets the ID of the order.
     *
     * @return the ID of the order
     */
    public String getId() {
        return _id;
    }

    /**
     * Gets the ID of the student who submitted the order.
     *
     * @return the ID of the student who submitted the order
     */
    public String getStudentId() {
        return _studentId;
    }

    /**
     * Gets the ID of the printer on which the document was printed.
     *
     * @return the ID of the printer on which the document was printed
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
     * Gets whether the document was printed single or double sided.
     *
     * @return whether the document was printed single or double sided
     */
    public boolean isSingleSided() {
        return _singleSided;
    }

    /**
     * Gets the time at which the order was submitted.
     *
     * @return the time at which the order was submitted
     */
    public LocalDateTime getTimeOrdered() {
        return _timeOrdered;
    }

    /**
     * Gets whether the order was completed successfully.
     *
     * @return whether the order was completed successfully
     */
    public boolean isDone() {
        return _done;
    }

    /**
     * Sets whether the order was completed successfully.
     *
     * @param done whether the order was completed successfully
     */
    public void setDone(boolean done) {
        this._done = done;
    }

    /**
     * Calculates the cost of the order, based on the number of pages printed
     * and the cost of the paper used.
     *
     * @return the cost of the order
     * @throws EnumConstantNotPresentException if the paper size is not a valid
     *                                         enum constant
     */
    public double getCost() throws EnumConstantNotPresentException {
        return this._pageNumbers.size() *
                this._numberOfCopies *
                PaperSize.valueOf(this._paperSize).getValue();
    }
}

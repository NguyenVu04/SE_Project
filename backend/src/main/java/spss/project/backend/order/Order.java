package spss.project.backend.order;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A class representing an order from a student to print a document.
 */
@Document(collection = "Orders")
public class Order {
    /**
     * The ID of the order.
     */
    @Id
    private String id;

    /**
     * The ID of the student who submitted the order.
     */
    private String studentId;

    /**
     * The ID of the printer on which the document was printed.
     */
    private String printerId;

    /**
     * The ID of the document which was printed.
     */
    private String documentId;

    /**
     * The size of the paper which the document was printed on.
     */
    private String paperSize;

    /**
     * The page numbers of the document which were printed.
     */
    private List<Integer> pageNumbers;

    /**
     * The number of copies of the document which were printed.
     */
    private int numberOfCopies;

    /**
     * Whether the document was printed single or double sided.
     */
    private boolean singleSided;

    /**
     * The time at which the order was submitted.
     */
    private LocalDateTime timeOrdered;

    /**
     * Whether the order was completed successfully.
     */
    private boolean done;

    /**
     * Creates a new order.
     *
     * @param studentId      the ID of the student who submitted the order
     * @param printerId      the ID of the printer on which the document was printed
     * @param documentId     the ID of the document which was printed
     * @param paperSize      the size of the paper which the document was printed on
     * @param pageNumbers    the page numbers of the document which were printed
     * @param numberOfCopies the number of copies of the document which were printed
     * @param singleSided     whether the document was printed single or double sided
     * @param timeOrdered    the time at which the order was submitted
     * @param done           whether the order was completed successfully
     */
    public Order(
            String studentId,
            String printerId,
            String documentId,
            String paperSize,
            List<Integer> pageNumbers,
            int numberOfCopies,
            boolean singleSided,
            LocalDateTime timeOrdered,
            boolean done) {

        this.studentId = studentId;
        this.printerId = printerId;
        this.documentId = documentId;
        this.paperSize = paperSize;
        this.pageNumbers = pageNumbers;
        this.numberOfCopies = numberOfCopies;
        this.singleSided = singleSided;
        this.timeOrdered = timeOrdered;
        this.done = done;
    }

    /**
     * Gets the ID of the order.
     *
     * @return the ID of the order
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the ID of the student who submitted the order.
     *
     * @return the ID of the student who submitted the order
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the ID of the printer on which the document was printed.
     *
     * @return the ID of the printer on which the document was printed
     */
    public String getPrinterId() {
        return printerId;
    }

    /**
     * Gets the ID of the document which was printed.
     *
     * @return the ID of the document which was printed
     */
    public String getDocumentId() {
        return documentId;
    }

    /**
     * Gets the size of the paper which the document was printed on.
     *
     * @return the size of the paper which the document was printed on
     */
    public String getPaperSize() {
        return paperSize;
    }

    /**
     * Gets the page numbers of the document which were printed.
     *
     * @return the page numbers of the document which were printed
     */
    public List<Integer> getPageNumbers() {
        return Collections.unmodifiableList(this.pageNumbers);
    }

    /**
     * Gets the number of copies of the document which were printed.
     *
     * @return the number of copies of the document which were printed
     */
    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    /**
     * Gets whether the document was printed single or double sided.
     *
     * @return whether the document was printed single or double sided
     */
    public boolean isSingleSided() {
        return singleSided;
    }

    /**
     * Gets the time at which the order was submitted.
     *
     * @return the time at which the order was submitted
     */
    public LocalDateTime getTimeOrdered() {
        return timeOrdered;
    }

    /**
     * Gets whether the order was completed successfully.
     *
     * @return whether the order was completed successfully
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Sets whether the order was completed successfully.
     *
     * @param done whether the order was completed successfully
     */
    public void setDone(boolean done) {
        this.done = done;
    }
}

package spss.project.backend.history.printing;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import spss.project.backend.document.PaperSize;

@Document(collection = "PrintingHistory")
/**
 * A class representing a printing history item.
 */
public class PrintingHistoryItem {
    /**
     * The ID of the printing history item.
     */
    @Id
    private String id;

    /**
     * The ID of the student who requested the printing job.
     */
    private String studentId;

    /**
     * The ID of the printer which the document was printed on.
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
     * The time at which the printing job was requested.
     */
    private LocalDateTime timeOrdered;

    /**
     * The time at which the printing job was completed.
     */
    private LocalDateTime timePrinted;

    /**
     * Whether the printing job was successful or not.
     */
    private boolean successful;

    /**
     * Creates a new printing history item.
     * 
     * @param studentId      the ID of the student who requested the printing job
     * @param printerId      the ID of the printer which the document was printed on
     * @param documentId     the ID of the document which was printed
     * @param paperSize      the size of the paper which the document was printed on
     * @param pageNumbers    the page numbers of the document which were printed
     * @param numberOfCopies the number of copies of the document which were printed
     * @param singleSided    whether the document was printed single or double sided
     * @param timeOrdered    the time at which the printing job was requested
     * @param timePrinted    the time at which the printing job was completed
     */
    public PrintingHistoryItem(
            String studentId,
            String printerId,
            String documentId,
            PaperSize paperSize,
            List<Integer> pageNumbers,
            int numberOfCopies,
            boolean singleSided,
            LocalDateTime timeOrdered,
            LocalDateTime timePrinted,
            boolean successful) {

        this.studentId = studentId;
        this.printerId = printerId;
        this.documentId = documentId;
        this.paperSize = paperSize.toString();
        this.pageNumbers = pageNumbers;
        this.numberOfCopies = numberOfCopies;
        this.singleSided = singleSided;
        this.timeOrdered = timeOrdered;
        this.timePrinted = timePrinted;
        this.successful = successful;
    }

    /**
     * Gets the ID of the printing history item.
     * 
     * @return the ID of the printing history item
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the ID of the student who requested the printing job.
     * 
     * @return the ID of the student who requested the printing job
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the ID of the printer which the document was printed on.
     * 
     * @return the ID of the printer which the document was printed on
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
     * Gets the time at which the printing job was requested.
     * 
     * @return the time at which the printing job was requested
     */
    public LocalDateTime getTimeOrdered() {
        return timeOrdered;
    }

    /**
     * Gets the time at which the printing job was completed.
     * 
     * @return the time at which the printing job was completed
     */
    public LocalDateTime getTimePrinted() {
        return timePrinted;
    }

    /**
     * Checks if the printing job was single sided.
     * 
     * @return true if the printing job was single sided, false if it was double
     *         sided
     */
    public boolean isSingleSided() {
        return singleSided;
    }

    /**
     * Checks if the printing job was successful.
     * 
     * @return true if the printing job was successful, false if it was not
     */
    public boolean isSuccessful() {
        return successful;
    }
}

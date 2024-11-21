package spss.project.backend.history.printing;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import spss.project.backend.document.PaperSize;

/**
 * This class provides methods for interacting with the printing history of the
 * students. The printing history is stored in a MongoDB database and can be
 * accessed using the methods of this class.
 */
@Service
public class PrintingHistoryService {
    /**
     * This constructor is intentionally empty. This class should not be
     * instantiated as it is a service class and all its methods are static.
     */
    protected PrintingHistoryService() {}

    @Autowired
    private PrintingHistoryRepository repo;

    /**
     * Saves a printing history item in the database.
     *
     * @param studentId      the id of the student who printed the document
     * @param printerId      the id of the printer on which the document was
     *                       printed
     * @param documentId     the id of the document which was printed
     * @param paperSize      the size of the paper the document was printed on
     * @param pageNumbers    the page numbers of the document which were printed
     * @param numberOfCopies the number of copies of the document which were
     *                       printed
     * @param singleSided    whether the document was printed single or double
     *                       sided
     * @param timeOrdered    the time at which the printing job was requested
     * @param timePrinted    the time at which the printing job was completed
     * @param successful     whether the printing job was successful
     * @return the saved printing history item
     * @throws Exception if an error occurs while saving the printing history
     *                   item
     */
    public PrintingHistoryItem save(
            String studentId,
            String printerId,
            String documentId,
            PaperSize paperSize,
            List<Integer> pageNumbers,
            int numberOfCopies,
            boolean singleSided,
            LocalDateTime timeOrdered,
            LocalDateTime timePrinted,
            boolean successful) throws Exception {

        PrintingHistoryItem item = new PrintingHistoryItem(
                studentId,
                printerId,
                documentId,
                paperSize,
                pageNumbers,
                numberOfCopies,
                singleSided,
                timeOrdered,
                timePrinted,
                successful);

        return repo.save(item);
    }

    /**
     * Finds a printing history item by its id.
     *
     * @param id the id of the printing history item to find
     * @return the printing history item with the given id, or null if no such
     *         item exists
     * @throws Exception if an error occurs while retrieving the printing
     *                   history item
     */
    public PrintingHistoryItem getHistoryItem(String id) throws Exception {
        return repo.findById(id).orElse(null);
    }

    /**
     * Finds all printing history items which were printed between the given
     * times.
     *
     * @param from the start time of the range (inclusive)
     * @param to   the end time of the range (inclusive)
     * @return a list of all printing history items which were printed between the
     *         given times
     * @throws Exception if an error occurs while retrieving the printing
     *                   history items
     */
    public List<PrintingHistoryItem> getPrintingHistory(LocalDateTime from, LocalDateTime to) throws Exception {
        return repo.findByTimeOrderedBetween(from, to, Sort.by(Direction.DESC, "timeOrdered"));
    }

    /**
     * Finds all printing history items which were printed on the given printer
     * between the given times.
     *
     * @param printerId the id of the printer on which the printing jobs were
     *                  printed
     * @param from      the start time of the range (inclusive)
     * @param to        the end time of the range (inclusive)
     * @return a list of all printing history items which were printed on the
     *         given printer between the given times, sorted by the time at which
     *         the printing jobs were completed in descending order
     * @throws Exception if an error occurs while retrieving the printing
     *                   history items
     */
    public List<PrintingHistoryItem> getPrinterPrintingHistory(String printerId, LocalDateTime from, LocalDateTime to)
            throws Exception {
        return repo.findByPrinterIdAndTimePrintedBetween(
                printerId,
                from,
                to,
                Sort.by(Direction.DESC, "timePrinted"));
    }


    /**
     * Finds all printing history items of a student.
     *
     * @param studentId the id of the student whose printing history to retrieve
     * @return a list of all printing history items of the student
     * @throws Exception if an error occurs while retrieving the printing
     *                   history items
     */
    public List<PrintingHistoryItem> getStudentPrintingHistory(String studentId) throws Exception {
        return repo.findAllByStudentId(studentId);
    }

    /**
     * Returns the repository used by this service.
     *
     * @return the repository used by this service
     */
    public PrintingHistoryRepository getRepo() {
        return repo;
    }
}

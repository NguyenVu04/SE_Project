package spss.project.backend.history.printing;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository for accessing and manipulating printing history items in the
 * database.
 */
@Repository
public interface PrintingHistoryRepository extends MongoRepository<PrintingHistoryItem, String> {

    /**
     * Finds all printing history items which were submitted by the given
     * student.
     *
     * @param studentId the id of the student to find the printing history items
     * @param sort the sort order of the printing history items
     * @return the printing history items of the student
     */
    public List<PrintingHistoryItem> findByStudentId(String studentId, Sort sort);

    /**
     * Finds all printing history items which were printed between the given
     * times.
     *
     * @param from the start time of the range (inclusive)
     * @param to   the end time of the range (inclusive)
     * @param sort the sort order of the printing history items
     * @return a list of all printing history items which were printed between the
     *         given times, sorted by the time at which the printing jobs were
     *         completed in ascending order
     */
    public List<PrintingHistoryItem> findByTimeOrderedBetween(LocalDateTime from, LocalDateTime to, Sort sort);

    /**
     * Finds all printing history items which were printed on the given printer
     * between the given times.
     *
     * @param printerId the id of the printer on which the printing jobs were
     *                  printed
     * @param from      the start time of the range (inclusive)
     * @param to        the end time of the range (inclusive)
     * @param sort      the sort order of the printing history items
     * @return a list of all printing history items which were printed on the
     *         given printer between the given times, sorted by the time at which
     *         the printing jobs were completed in ascending order
     */
    public List<PrintingHistoryItem> findByPrinterIdAndTimePrintedBetween(String printerId, LocalDateTime from, LocalDateTime to, Sort sort);
}

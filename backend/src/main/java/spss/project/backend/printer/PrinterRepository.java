package spss.project.backend.printer;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository for accessing and manipulating printers in the database
 */
@Repository
public interface PrinterRepository extends MongoRepository<Printer, String> {
    /**
     * Finds all printers in the database which have the given active status.
     *
     * @param active the active status of the printers to find
     * @return a list of all printers which have the given active status
     */
    public List<Printer> findByActive(boolean active);
}
package spss.project.backend.printer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository for accessing and manipulating printers in the database
 */
@Repository
public interface PrinterRepository extends MongoRepository<Printer, String> {}
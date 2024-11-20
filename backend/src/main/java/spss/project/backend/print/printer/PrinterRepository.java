package spss.project.backend.print.printer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrinterRepository extends MongoRepository<Printer, String> {}
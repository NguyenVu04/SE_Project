package spss.project.backend.history.printing;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrintingHistoryRepository extends MongoRepository<PrintingHistoryItem, String> {
    public List<PrintingHistoryItem> findAllByStudentId(String studentId);
    public List<PrintingHistoryItem> findByTimeOrderedBetween(LocalDateTime from, LocalDateTime to);
}

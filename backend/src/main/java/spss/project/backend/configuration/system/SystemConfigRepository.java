package spss.project.backend.configuration.system;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemConfigRepository extends MongoRepository<SystemConfig, String> {
    public SystemConfig findTopByOrderByCreateAtDesc();
}

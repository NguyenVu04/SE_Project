package spss.project.backend.configuration.system;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface for the system configuration repository.
 */
@Repository
public interface SystemConfigRepository extends MongoRepository<SystemConfig, String> {

    /**
     * Finds the most recently created system configuration.
     *
     * @return the most recently created system configuration
     */
    public SystemConfig findTopByOrderByCreatedAtDesc();

}

package spss.project.backend.configuration.system;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
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

    /**
     * Finds all system configurations which were created between the given
     * times.
     * 
     * @param from the start time of the range (inclusive)
     * @param to   the end time of the range (inclusive)
     * @param sort the sort order of the system configurations
     * @return a list of all system configurations which were created between the
     *         given times, sorted by the time at which the configurations were
     *         created in ascending order
     */
    public List<SystemConfig> findByCreatedAtBetween(LocalDateTime from, LocalDateTime to, Sort sort);
}

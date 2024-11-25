package spss.project.backend.configuration.system;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 * Service class for managing system configurations.
 */
@Service
public class SystemConfigService {
    /**
     * Constructor for the SystemConfigService class. This is a protected
     * constructor
     * because this class should not be instantiated directly.
     */
    protected SystemConfigService() {
    }

    @Autowired
    private SystemConfigRepository repo;

    /**
     * Saves the current system configuration to the repository.
     *
     * @param defaultNumberOfPages the default number of pages
     * @param paperSupplyDay the paper supply day
     * @param createdBy      the user who created the configuration
     * @param fileTypes      the list of allowed file types
     * @return the saved SystemConfig object
     * @throws InvalidParameterException if paperSupplyDay is not between 1 and 28
     * @throws Exception                 if an error occurs while saving the
     *                                   configuration
     */
    public SystemConfig saveCurrentSystemConfig(
            int defaultNumberOfPages,
            int paperSupplyDay,
            String createdBy,
            List<String> fileTypes) throws InvalidParameterException, Exception {

        return repo.save(
                new SystemConfig(
                        defaultNumberOfPages,
                        paperSupplyDay,
                        createdBy,
                        fileTypes));
    }

    /**
     * Retrieves the most recently created system configuration.
     *
     * @return the most recent SystemConfig object
     */
    public SystemConfig getCurrentSystemConfig() {
        return repo.findTopByOrderByCreatedAtDesc();
    }

    /**
     * Retrieves all system configurations which were created between the given
     * times, sorted in descending order by the time at which the configurations
     * were created.
     *
     * @param from the start time of the range (inclusive)
     * @param to   the end time of the range (inclusive)
     * @return a list of system configurations which were created between the given
     *         times, sorted by the time at which the configurations were created in
     *         descending order
     */
    public List<SystemConfig> getSystemConfigHistory(LocalDateTime from, LocalDateTime to) {
        return repo.findByCreatedAtBetween(from, to, Sort.by(Direction.DESC, "createdAt"));
    }

    /**
     * Gets the repository for system configurations.
     *
     * @return the system configuration repository
     */
    public SystemConfigRepository getRepo() {
        return repo;
    }
}

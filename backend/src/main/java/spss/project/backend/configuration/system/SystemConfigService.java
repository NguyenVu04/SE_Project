package spss.project.backend.configuration.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemConfigService {
    @Autowired
    private SystemConfigRepository repo;

    /**
     * Retrieves the most recent SystemConfig object from the repository.
     *
     * @return the latest SystemConfig based on creation date
     */
    public SystemConfig getCurrentSystemConfig() {
        return repo.findTopByOrderByCreateAtDesc();
    }

    /**
     * @return the repository for {@link SystemConfig} objects
     */
    public SystemConfigRepository getRepo() {
        return repo;
    }
}
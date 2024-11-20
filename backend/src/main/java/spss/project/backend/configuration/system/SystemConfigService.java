package spss.project.backend.configuration.system;

import java.security.InvalidParameterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemConfigService {
    @Autowired
    private SystemConfigRepository repo;

    public SystemConfig saveCurrentSystemConfig(
            int paperSupplyDay,
            String createdBy,
            String cloudUrl) throws InvalidParameterException, Exception {

        return repo.save(
                new SystemConfig(
                        paperSupplyDay,
                        createdBy,
                        cloudUrl));
    }

    public SystemConfig getCurrentSystemConfig() {
        return repo.findTopByOrderByCreatedAtDesc();
    }

    public SystemConfigRepository getRepo() {
        return repo;
    }
}
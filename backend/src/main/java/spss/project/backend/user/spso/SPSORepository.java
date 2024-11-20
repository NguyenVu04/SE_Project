package spss.project.backend.user.spso;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SPSORepository extends MongoRepository<SPSO, String> {
    public SPSO findByEmail(String email);
}

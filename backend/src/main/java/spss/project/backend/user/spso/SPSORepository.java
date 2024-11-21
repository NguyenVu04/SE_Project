package spss.project.backend.user.spso;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository for accessing and manipulating SPSOs in the database.
 */
@Repository
public interface SPSORepository extends MongoRepository<SPSO, String> {

    /**
     * Finds a SPSO by its email.
     * 
     * @param email the email of the SPSO to find
     * @return the SPSO with the given email, or null if no such SPSO exists
     */
    public SPSO findByEmail(String email);
}

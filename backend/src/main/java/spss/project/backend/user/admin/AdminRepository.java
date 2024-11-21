package spss.project.backend.user.admin;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository for accessing and manipulating admins in the database
 */
@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {}

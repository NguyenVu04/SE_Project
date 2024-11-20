package spss.project.backend.user.student;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository for students.
 */
@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    /**
     * Finds a student by its email.
     *
     * @param email the email of the student to find
     * @return the student with the given email, or null if no such student exists
     */
    public Student findByEmail(String email);
}

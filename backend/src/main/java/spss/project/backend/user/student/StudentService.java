package spss.project.backend.user.student;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

/**
 * A service for working with students.
 */
@Service
public class StudentService {
    /**
     * The repository used to store and retrieve students.
     */
    @Autowired
    private StudentRepository repo;

    /**
     * Saves a student to the database.
     * 
     * @param email       the email of the student
     * @param firstName   the first name of the student
     * @param lastName    the last name of the student
     * @param dateOfBirth the date of birth of the student
     * @param balance     the balance of the student
     * @return the saved student
     * @throws Exception if an error occurs while saving the student
     */
    public Student save(
            String email,
            String firstName,
            String lastName,
            Date dateOfBirth,
            int balance) throws Exception {

        Student student = new Student(email, firstName, lastName, dateOfBirth, balance);
        return repo.save(student);
    }

    /**
     * Deletes a student from the database by its id.
     * 
     * @param id the id of the student to delete
     * @throws Exception if an error occurs while deleting the student
     */
    public void deleteStudent(String id) throws Exception {
        repo.deleteById(id);
    }

    /**
     * Finds a student by its id.
     * 
     * @param id the id of the student to find
     * @return the student with the given id, or null if no such student exists
     * @throws Exception if an error occurs while retrieving the student
     */
    public Student getStudent(String id) throws Exception {
        return repo.findById(id).orElse(null);
    }

    /**
     * Finds a student by its email.
     * 
     * @param email the email of the student to find
     * @return the student with the given email, or null if no such student exists
     * @throws Exception if an error occurs while retrieving the student
     */
    public Student getStudentByEmail(String email) throws Exception {
        return repo.findByEmail(email);
    }

    /**
     * Updates a student in the database.
     * 
     * @param id         the id of the student to update
     * @param email      the new email of the student
     * @param firstName  the new first name of the student
     * @param lastName   the new last name of the student
     * @param dateOfBirth the new date of birth of the student
     * @param balance    the new balance of the student
     * @return the updated student
     * @throws NotFoundException if the student with the given id does not exist
     * @throws Exception        if an error occurs while updating the student
     */
    public Student updateStudent(
            String id,
            String email,
            String firstName,
            String lastName,
            Date dateOfBirth,
            int balance) throws NotFoundException, Exception {

        Student student = this.getStudent(id);
        
        if (student == null) {
            throw new NotFoundException();
        }

        student.setEmail(email);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setDateOfBirth(dateOfBirth);
        student.setBalance(balance);
        return repo.save(student);
    }

    /**
     * Adds the given amount to the balance of the student with the given id.
     * 
     * @param id    the id of the student
     * @param amount the amount to add to the balance of the student
     * @throws NotFoundException if the student with the given id does not exist
     * @throws Exception        if an error occurs while updating the student
     */
    public void addBalance(String id, int amount) throws NotFoundException, Exception {
        Student student = this.getStudent(id);

        if (student == null) {
            throw new NotFoundException();
        }

        student.addBalance(amount);
        repo.save(student);
    }

    /**
     * Returns the repository used by this service.
     * 
     * @return the repository used by this service
     */
    public StudentRepository getRepo() {
        return repo;
    }
}

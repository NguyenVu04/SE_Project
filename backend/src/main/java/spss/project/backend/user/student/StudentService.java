package spss.project.backend.user.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * Protected constructor for StudentService.
     * This is used by the framework for dependency injection.
     */
    protected StudentService() {
    }

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
            LocalDate dateOfBirth,
            int balance) throws DuplicateKeyException, Exception {

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
     * @param id          the id of the student to update
     * @param firstName   the new first name of the student
     * @param lastName    the new last name of the student
     * @param dateOfBirth the new date of birth of the student
     * @return the updated student
     * @throws NotFoundException if the student with the given id does not exist
     * @throws Exception         if an error occurs while updating the student
     */
    public Student updateStudent(
            String id,
            String firstName,
            String lastName,
            LocalDate dateOfBirth) throws NotFoundException, Exception {

        Student student = this.getStudent(id);

        if (student == null) {
            throw new NotFoundException();
        }

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setDateOfBirth(dateOfBirth);
        return repo.save(student);
    }

    /**
     * Adds the given amount to the balance of the student with the given id.
     * 
     * @param id     the id of the student
     * @param amount the amount to add to the balance of the student
     * @throws NotFoundException if the student with the given id does not exist
     * @throws Exception         if an error occurs while updating the student
     */
    public void addBalance(String id, double amount) throws NotFoundException, Exception {
        Student student = this.getStudent(id);

        if (student == null) {
            throw new NotFoundException();
        }

        student.addBalance(amount);
        repo.save(student);
    }

    /**
     * Supplies all students with a number of pages as specified by the current
     * system configuration.
     * 
     * Retrieves the current system configuration to determine the default number of
     * pages to supply
     * to each student. The method iterates through all students in the repository,
     * adding the specified
     * number of pages to each student's balance.
     * @param numberOfPages the number of pages to supply
     * @throws Exception if an error occurs while retrieving the system
     *                   configuration or updating a student's balance
     */
    public void supplyAllStudents(double numberOfPages) throws Exception {
        Page<Student> students = repo.findAll(Pageable.ofSize(5));

        while (students.hasNext()) {
            List<Student> page = students.getContent();

            for (int i = 0; i < page.size(); i++) {
                Student student = page.get(i);
                student.addBalance(numberOfPages);
                repo.save(student);
            }
        }
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

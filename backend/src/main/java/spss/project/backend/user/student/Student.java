package spss.project.backend.user.student;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A class representing a student. 
 */
@Document(collection = "Students")
public class Student {

    /**
     * The ID of the student.
     */
    @Id
    private String id;

    /**
     * The email of the student.
     */
    @Indexed(name = "student_email", unique = true)
    private String email;

    /**
     * The first name of the student.
     */
    private String firstName;

    /**
     * The last name of the student.
     */
    private String lastName;

    /**
     * The date of birth of the student.
     */
    private LocalDate dateOfBirth;

    /**
     * The balance of the student.
     */
    private int balance;

    /**
     * Constructs a new Student object with the given parameters.
     *
     * @param email      the email of the student
     * @param firstName  the first name of the student
     * @param lastName   the last name of the student
     * @param dateOfBirth the date of birth of the student
     * @param balance    the balance of the student
     */
    public Student(
            String email,
            String firstName,
            String lastName,
            LocalDate dateOfBirth,
            int balance) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.balance = balance;
    }

    /**
     * Gets the ID of the student.
     *
     * @return the ID of the student
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the email of the student.
     * 
     * @return the email of the student
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the first name of the student.
     * 
     * @return the first name of the student
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the student.
     * 
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the student.
     * 
     * @return the last name of the student
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the student.
     * 
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the date of birth of the student.
     * 
     * @return the date of birth of the student
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the student.
     * 
     * @param dateOfBirth the date of birth to set
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the balance of the student.
     * 
     * @return the balance of the student
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the student. This should only be used by the
     * repository, as it does not perform any validation.
     * 
     * @param balance the balance to set
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Adds the given amount to the student's balance.
     * 
     * @param amount the amount to add to the balance
     */
    public void addBalance(int amount) {
        this.balance += amount;
    }
}

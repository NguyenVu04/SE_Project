package spss.project.backend.user.student;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import jakarta.validation.constraints.Min;

/**
 * A class representing a student. 
 */
@Document("Students")
public class Student {

    /**
     * The ID of the student.
     */
    @MongoId
    private String _id;

    /**
     * The email of the student.
     */
    @Indexed(name = "student_email", unique = true)
    private String _email;

    /**
     * The first name of the student.
     */
    private String _firstName;

    /**
     * The last name of the student.
     */
    private String _lastName;

    /**
     * The date of birth of the student.
     */
    private LocalDate _dateOfBirth;

    /**
     * The balance of the student.
     */
    @Min(0)
    private double _balance;

    /**
     * Constructs a new Student object with the given parameters.
     *
     * @param _email      the email of the student
     * @param _firstName  the first name of the student
     * @param _lastName   the last name of the student
     * @param _dateOfBirth the date of birth of the student
     * @param _balance    the balance of the student
     */
    public Student(
            String _email,
            String _firstName,
            String _lastName,
            LocalDate _dateOfBirth,
            double _balance) {
        this._email = _email;
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._dateOfBirth = _dateOfBirth;
        this._balance = _balance;
    }

    /**
     * Gets the ID of the student.
     *
     * @return the ID of the student
     */
    public String getId() {
        return _id;
    }

    /**
     * Gets the email of the student.
     * 
     * @return the email of the student
     */
    public String getEmail() {
        return _email;
    }

    /**
     * Gets the first name of the student.
     * 
     * @return the first name of the student
     */
    public String getFirstName() {
        return _firstName;
    }

    /**
     * Sets the first name of the student.
     * 
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this._firstName = firstName;
    }

    /**
     * Gets the last name of the student.
     * 
     * @return the last name of the student
     */
    public String getLastName() {
        return _lastName;
    }

    /**
     * Sets the last name of the student.
     * 
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this._lastName = lastName;
    }

    /**
     * Gets the date of birth of the student.
     * 
     * @return the date of birth of the student
     */
    public LocalDate getDateOfBirth() {
        return _dateOfBirth;
    }

    /**
     * Sets the date of birth of the student.
     * 
     * @param dateOfBirth the date of birth to set
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this._dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the balance of the student.
     * 
     * @return the balance of the student
     */
    public double getBalance() {
        return _balance;
    }

    /**
     * Sets the balance of the student. This should only be used by the
     * repository, as it does not perform any validation.
     * 
     * @param balance the balance to set
     */
    public void setBalance(int balance) {
        this._balance = balance;
    }

    /**
     * Adds the given amount to the student's balance.
     * 
     * @param amount the amount to add to the balance
     */
    public void addBalance(double amount) {
        this._balance += amount;
    }
}

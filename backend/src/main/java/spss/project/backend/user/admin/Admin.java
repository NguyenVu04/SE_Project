package spss.project.backend.user.admin;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A class representing an admin user in the system.
 */
@Document(collection = "Admins")
public class Admin {
    /**
     * The unique identifier of the admin, which is their email address.
     */
    @Id
    private String email;
    /**
     * The first name of the admin.
     */
    private String firstName;
    /**
     * The last name of the admin.
     */
    private String lastName;

    /**
     * Creates a new admin.
     *
     * @param email    the email address of the admin
     * @param firstName the first name of the admin
     * @param lastName  the last name of the admin
     */
    public Admin(
            String email,
            String firstName,
            String lastName) {

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets the unique identifier of the admin.
     *
     * @return the email address of the admin
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the first name of the admin.
     *
     * @return the first name of the admin
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the admin.
     *
     * @param firstName the new first name of the admin
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the admin.
     *
     * @return the last name of the admin
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the admin.
     *
     * @param lastName the new last name of the admin
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

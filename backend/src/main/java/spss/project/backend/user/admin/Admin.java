package spss.project.backend.user.admin;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * A class representing an admin user in the system.
 */
@Document(collection = "Admins")
public class Admin {
    /**
     * The unique identifier of the admin, which is their email address.
     */
    @MongoId
    private String _email;
    /**
     * The first name of the admin.
     */
    private String _firstName;
    /**
     * The last name of the admin.
     */
    private String _lastName;

    /**
     * Creates a new admin.
     *
     * @param _email    the email address of the admin
     * @param _firstName the first name of the admin
     * @param _lastName  the last name of the admin
     */
    public Admin(
            String _email,
            String _firstName,
            String _lastName) {

        this._email = _email;
        this._firstName = _firstName;
        this._lastName = _lastName;
    }

    /**
     * Gets the unique identifier of the admin.
     *
     * @return the email address of the admin
     */
    public String getEmail() {
        return _email;
    }

    /**
     * Gets the first name of the admin.
     *
     * @return the first name of the admin
     */
    public String getFirstName() {
        return _firstName;
    }

    /**
     * Sets the first name of the admin.
     *
     * @param firstName the new first name of the admin
     */
    public void setFirstName(String firstName) {
        this._firstName = firstName;
    }

    /**
     * Gets the last name of the admin.
     *
     * @return the last name of the admin
     */
    public String getLastName() {
        return _lastName;
    }

    /**
     * Sets the last name of the admin.
     *
     * @param lastName the new last name of the admin
     */
    public void setLastName(String lastName) {
        this._lastName = lastName;
    }
}

package spss.project.backend.user.spso;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * Represents a Smart Printing Service Officer (SPSO).
 */
@Document(collection = "SPSOs")
public class SPSO {
    /**
     * The unique identifier for the SPSO.
     */
    @MongoId
    private String _id;

    @Indexed(name = "spso_email", unique = true)
    /**
     * The email address of the SPSO.
     */
    private String _email;

    /**
     * The first name of the SPSO.
     */
    private String _firstName;

    /**
     * The last name of the SPSO.
     */
    private String _lastName;

    /**
     * Constructs a new SPSO based on the given email address, first name and
     * last name.
     *
     * @param email     the email address of the SPSO
     * @param firstName the first name of the SPSO
     * @param lastName  the last name of the SPSO
     */
    public SPSO(
            String _email,
            String _firstName,
            String _lastName) {
        this._email = _email;
        this._firstName = _firstName;
        this._lastName = _lastName;
    }

    /**
     * Gets the unique identifier for the SPSO.
     *
     * @return the unique identifier for the SPSO
     */
    public String getId() {
        return _id;
    }

    /**
     * Gets the email address of the SPSO.
     *
     * @return the email address of the SPSO
     */
    public String getEmail() {
        return _email;
    }

    /**
     * Gets the first name of the SPSO.
     *
     * @return the first name of the SPSO
     */
    public String getFirstName() {
        return _firstName;
    }

    /**
     * Sets the first name of the SPSO.
     *
     * @param firstName the new first name of the SPSO
     */
    public void setFirstName(String firstName) {
        this._firstName = firstName;
    }

    /**
     * Gets the last name of the SPSO.
     *
     * @return the last name of the SPSO
     */
    public String getLastName() {
        return _lastName;
    }

    /**
     * Sets the last name of the SPSO.
     *
     * @param lastName the new last name of the SPSO
     */
    public void setLastName(String lastName) {
        this._lastName = lastName;
    }
}

package spss.project.backend.user.spso;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a Smart Printing Service Officer (SPSO).
 */
@Document(collection = "SPSOs")
public class SPSO {
    @Id
    /**
     * The unique identifier for the SPSO.
     */
    private String id;

    @Indexed(name = "spso_email", unique = true)
    /**
     * The email address of the SPSO.
     */
    private String email;

    /**
     * The first name of the SPSO.
     */
    private String firstName;

    /**
     * The last name of the SPSO.
     */
    private String lastName;

    /**
     * Constructs a new SPSO based on the given email address, first name and
     * last name.
     *
     * @param email     the email address of the SPSO
     * @param firstName the first name of the SPSO
     * @param lastName  the last name of the SPSO
     */
    public SPSO(
            String email,
            String firstName,
            String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets the unique identifier for the SPSO.
     *
     * @return the unique identifier for the SPSO
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the email address of the SPSO.
     *
     * @return the email address of the SPSO
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the first name of the SPSO.
     *
     * @return the first name of the SPSO
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the SPSO.
     *
     * @param firstName the new first name of the SPSO
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the SPSO.
     *
     * @return the last name of the SPSO
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the SPSO.
     *
     * @param lastName the new last name of the SPSO
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

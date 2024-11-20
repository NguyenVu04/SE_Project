package spss.project.backend.user.spso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

/**
 * A service for working with SPSOs.
 */
@Service
public class SPSOService {

    /**
     * The repository for SPSOs.
     */
    @Autowired
    private SPSORepository repo;

    /**
     * Saves a SPSO to the database.
     *
     * @param email       the email of the SPSO
     * @param firstName   the first name of the SPSO
     * @param lastName    the last name of the SPSO
     * @return the saved SPSO
     * @throws Exception if an error occurs while saving the SPSO
     */
    public SPSO save(
            String email,
            String firstName,
            String lastName) throws Exception {

        SPSO spso = new SPSO(email,
                firstName,
                lastName);
        return repo.save(spso);
    }

    /**
     * Finds a SPSO by its email.
     *
     * @param email the email of the SPSO to find
     * @return the SPSO with the given email, or null if no such SPSO exists
     */
    public SPSO getSPSO(String email) {
        return repo.findByEmail(email);
    }

    /**
     * Deletes a SPSO from the database by its email.
     *
     * @param email the email of the SPSO to delete
     * @throws Exception if an error occurs while deleting the SPSO
     */
    public void deleteSPSO(String email) throws Exception {
        repo.deleteById(this.getSPSO(email).getId());
    }

    /**
     * Updates a SPSO in the database.
     *
     * @param email       the email of the SPSO to update
     * @param firstName   the new first name of the SPSO
     * @param lastName    the new last name of the SPSO
     * @return the updated SPSO
     * @throws NotFoundException if the SPSO with the given email does not exist
     * @throws Exception        if an error occurs while updating the SPSO
     */
    public SPSO updateSPSO(String email, String firstName, String lastName) throws NotFoundException, Exception {
        SPSO spso = this.getSPSO(email);

        if (spso == null) {
            throw new NotFoundException();
        }

        spso.setFirstName(firstName);
        spso.setLastName(lastName);
        return repo.save(spso);
    }

    /**
     * Gets the repository for SPSOs.
     *
     * @return the repository for SPSOs
     */
    public SPSORepository getRepo() {
        return repo;
    }
}

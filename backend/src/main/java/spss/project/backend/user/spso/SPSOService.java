package spss.project.backend.user.spso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
     * Constructor for the SPSOService class. This class is a singleton, and
     * should not be instantiated directly.
     */
    protected SPSOService() {}

    /**
     * Saves a SPSO to the database.
     *
     * @param email     the email of the SPSO
     * @param firstName the first name of the SPSO
     * @param lastName  the last name of the SPSO
     * @return the saved SPSO
     * @throws Exception if an error occurs while saving the SPSO
     */
    public SPSO save(
            String email,
            String firstName,
            String lastName) throws DuplicateKeyException, Exception {

        SPSO spso = new SPSO(email,
                firstName,
                lastName);
        return repo.save(spso);
    }

    /**
     * Finds a SPSO by its id.
     *
     * @param id the id of the SPSO to find
     * @return the SPSO with the given id, or null if no such SPSO exists
     * @throws Exception if an error occurs while retrieving the SPSO
     */
    public SPSO getSPSO(String id) throws Exception {
        return repo.findById(id).orElse(null);
    }

    /**
     * Finds ID of a SPSO by its email.
     *
     * @param email the email of the SPSO to find
     * @return the SPSO email
     * @throws Exception if an error occurs while getting the SPSO or SPSO not exists
     */
    public String getSPSOId(String email) throws Exception {
        return repo.findByEmail(email).getId();
    }

    /**
     * Deletes a SPSO from the database by its id.
     *
     * @param id the id of the SPSO to delete
     * @throws Exception if an error occurs while deleting the SPSO
     */
    public void deleteSPSO(String id) throws Exception {
        repo.deleteById(id);
    }

    /**
     * Updates a SPSO in the database.
     *
     * @param id        the id of the SPSO to update
     * @param firstName the new first name of the SPSO
     * @param lastName  the new last name of the SPSO
     * @return the updated SPSO
     * @throws NotFoundException if the SPSO with the given email does not exist
     * @throws Exception         if an error occurs while updating the SPSO
     */
    public SPSO updateSPSO(String id, String firstName, String lastName) throws NotFoundException, Exception {
        SPSO spso = this.getSPSO(id);

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

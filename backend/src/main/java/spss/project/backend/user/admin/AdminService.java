package spss.project.backend.user.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

/**
 * A service for working with admins.
 */
@Service
public class AdminService {
    /**
     * The repository for admins.
     */
    @Autowired
    private AdminRepository repo;

    /**
     * Saves an admin to the database.
     * 
     * @param email       the email of the admin
     * @param firstName   the first name of the admin
     * @param lastName    the last name of the admin
     * @return the saved admin
     * @throws Exception if an error occurs while saving the admin
     */
    public Admin save(
            String email,
            String firstName,
            String lastName) throws Exception {

        Admin admin = new Admin(email, firstName, lastName);
        return repo.save(admin);
    }

    /**
     * Deletes an admin from the database by its email.
     * 
     * @param email the email of the admin to delete
     * @throws Exception if an error occurs while deleting the admin
     */
    public void deleteAdmin(String email) throws Exception {
        repo.deleteById(email);
    }

    /**
     * Updates an admin in the database.
     * 
     * @param email       the email of the admin to update
     * @param firstName   the new first name of the admin
     * @param lastName    the new last name of the admin
     * @return the updated admin
     * @throws NotFoundException if the admin with the given email does not exist
     * @throws Exception        if an error occurs while updating the admin
     */
    public Admin updateAdmin(
            String email,
            String firstName,
            String lastName) throws NotFoundException, Exception {
            
        Admin admin = this.getAdmin(email);

        if(admin == null) {
            throw new NotFoundException();
        }

        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        return repo.save(admin);
    }

    /**
     * Finds an admin by its email.
     * 
     * @param email the email of the admin to find
     * @return the admin with the given email, or null if no such admin exists
     * @throws Exception if an error occurs while retrieving the admin
     */
    public Admin getAdmin(String email) throws Exception {
        return repo.findById(email)
                .orElse(null);
    }

    /**
     * Gets the repository for admins.
     * 
     * @return the repository for admins
     */
    public AdminRepository getRepo() {
        return repo;
    }
}

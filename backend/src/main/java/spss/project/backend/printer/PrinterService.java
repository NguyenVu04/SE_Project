package spss.project.backend.printer;

import java.util.List;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrinterService {
    @Autowired
    private PrinterRepository repo;

    /**
     * Saves a printer to the database.
     * 
     * @param id           the id to assign to the printer
     * @param url          the URL of the printer
     * @param model        the model of the printer
     * @param description  a description of the printer
     * @param campusName   the name of the campus where the printer is
     *                     located
     * @param buildingName the name of the building where the printer is
     *                     located
     * @param roomNumber   the room number where the printer is located
     * @param active       whether the printer is active or not
     * @return the saved printer
     * @throws Exception if the printer cannot be saved
     */
    public Printer save(
            String id,
            String url,
            String model,
            String description,
            String campusName,
            String buildingName,
            String roomNumber,
            boolean active) throws Exception {

        Printer printer = new Printer(url, model, description, campusName, buildingName, roomNumber, active);
        return repo.save(printer);
    }

    /**
     * Finds all printers in the database.
     *
     * @return a list of all printers
     * @throws Exception if an error occurs while retrieving the printers
     */
    public List<Printer> getPrinters() throws Exception {
        return repo.findAll();
    }

    /**
     * Finds a printer by its id.
     * 
     * @param id the id of the printer to find
     * @return the printer with the given id, or null if no such printer exists
     * @throws Exception if an error occurs while retrieving the printer
     */
    public Printer getPrinter(String id) throws Exception {
        return repo.findById(id).orElse(null);
    }

    /**
     * Deletes a printer from the database by its id.
     * 
     * @param id the id of the printer to delete
     * @throws Exception if an error occurs while deleting the printer
     */
    public void deleteById(String id) throws Exception {
        repo.deleteById(id);
    }

    /**
     * Activates a printer with the given id in the database, making it
     * available for printing.
     *
     * @param id the id of the printer to activate
     * @throws Exception if an error occurs while activating the printer
     */
    public void activatePrinter(String id) throws Exception {
        Printer printer = this.getPrinter(id);
        printer.setActive(true);
        repo.save(printer);
    }

    /**
     * Deactivates a printer with the given id in the database, making it
     * unavailable for printing.
     * 
     * @param id the id of the printer to deactivate
     * @throws Exception if an error occurs while deactivating the printer
     */
    public void deactivatePrinter(String id) throws Exception {
        Printer printer = this.getPrinter(id);
        printer.setActive(false);
        repo.save(printer);
    }

    /**
     * Updates a printer with the given id in the database.
     * 
     * @param id           the id of the printer to update
     * @param url          the URL of the printer
     * @param model        the model of the printer
     * @param description  a description of the printer
     * @param campusName   the name of the campus where the printer is
     *                     located
     * @param buildingName the name of the building where the printer is
     *                     located
     * @param roomNumber   the room number where the printer is located
     * @param active       whether the printer is active or not
     * @throws NameNotFoundException if the printer with the given id cannot be
     *                               found
     * @throws Exception             if an error occurs while updating the printer
     */
    public void updatePrinter(
            String id,
            String url,
            String model,
            String description,
            String campusName,
            String buildingName,
            String roomNumber,
            boolean active) throws NameNotFoundException, Exception {

        Printer printer = this.getPrinter(id);

        if (printer == null) {
            throw new NameNotFoundException();
        }

        printer.setUrl(url);
        printer.setModel(model);
        printer.setDescription(description);
        printer.setCampusName(campusName);
        printer.setBuildingName(buildingName);
        printer.setRoomNumber(roomNumber);
        printer.setActive(active);

        repo.save(printer);
    }

    /**
     * @return the repository for {@link Printer} objects
     */
    public PrinterRepository getRepo() {
        return repo;
    }
}

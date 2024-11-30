package spss.project.backend.printer;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * A class representing a printer.
 */
@Document(collection = "Printers")
public class Printer {

    /**
     * The id of the printer, used to identify the printer in the database.
     */
    @MongoId
    private String id;

    /**
     * The manufacturer of the printer.
     */
    private String manufacturer;

    /**
     * The model of the printer.
     */
    private String model;

    /**
     * A description of the printer.
     */
    private String description;

    /**
     * The name of the campus where the printer is located.
     */
    private String campusName;

    /**
     * The name of the building where the printer is located.
     */
    private String buildingName;

    /**
     * The room number where the printer is located.
     */
    private String roomNumber;

    /**
     * Whether the printer is active or not.
     */
    private boolean active;

    /**
     * Constructs a new Printer object.
     * 
     * @param model        the model of the printer
     * @param description  a description of the printer
     * @param campusName   the name of the campus where the printer is
     *                     located
     * @param buildingName the name of the building where the printer is
     *                     located
     * @param roomNumber   the room number where the printer is located
     * @param active       whether the printer is active or not
     */
    public Printer(
            String manufacturer,
            String model,
            String description,
            String campusName,
            String buildingName,
            String roomNumber,
            boolean active) {

        this.manufacturer = manufacturer;
        this.model = model;
        this.description = description;
        this.campusName = campusName;
        this.buildingName = buildingName;
        this.roomNumber = roomNumber;
        this.active = active;
    }

    /**
     * Gets the id of the printer.
     * 
     * @return the id of the printer
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the manufacturer of the printer.
     * 
     * @return the manufacturer of the printer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the manufacturer of the printer.
     * 
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Gets the model of the printer.
     * 
     * @return the model of the printer
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model of the printer.
     * 
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the description of the printer.
     * 
     * @return the description of the printer
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the printer.
     * 
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the name of the campus where the printer is located.
     * 
     * @return the name of the campus
     */
    public String getCampusName() {
        return campusName;
    }

    /**
     * Sets the name of the campus where the printer is located.
     * 
     * @param campusName the name of the campus
     */
    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    /**
     * Gets the name of the building where the printer is located.
     * 
     * @return the name of the building
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * Sets the name of the building where the printer is located.
     * 
     * @param buildingName the name of the building
     */
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    /**
     * Gets the room number where the printer is located.
     * 
     * @return the room number
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets the room number where the printer is located.
     * 
     * @param roomNumber the room number to set
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Checks if the printer is active.
     *
     * @return true if the printer is active, false otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the active status of the printer.
     * 
     * @param active the new active status
     */
    public void setActive(boolean active) {
        this.active = active;
    }
}
package spss.project.backend.printer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import spss.project.backend.document.PaperSize;

/**
 * A class representing a printer.
 */
@Document(collection = "Printers")
public class Printer {

    /**
     * The id of the printer, used to identify the printer in the database.
     */
    @Id
    private String id;

    /**
     * The URL of the printer's REST API.
     */
    @Indexed(name = "printer_url", unique = true)
    private String url;

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
     * @param url          the URL of the printer
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
            String url,
            String model,
            String description,
            String campusName,
            String buildingName,
            String roomNumber,
            boolean active) {

        this.url = url;
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
     * Gets the URL of the printer.
     * 
     * @return the URL of the printer
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL of the printer.
     * 
     * @param url the URL to set
     */
    public void setUrl(String url) {
        this.url = url;
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

    /**
     * Prints a document using the printer's REST API.
     *
     * @param orderId        the ID of the order
     * @param fileUrl        the URL of the document to print
     * @param paperSize      the size of the paper to print on
     * @param pageNumbers    the page numbers of the document to print
     * @param singleSided     whether to print single or double sided
     * @param numberOfCopies the number of copies to print
     * @throws Exception if the document cannot be printed
     */
    public void printDocument(
            String orderId,
            String fileUrl,
            PaperSize paperSize,
            List<Integer> pageNumbers,
            boolean singleSided,
            int numberOfCopies) throws Exception {

        Map<String, Object> printInfo = new HashMap<>();

        printInfo.put("orderId", orderId);
        printInfo.put("fileUrl", fileUrl);
        printInfo.put("paperSize", paperSize.toString());
        printInfo.put("pageNumbers", pageNumbers);
        printInfo.put("singleSide", singleSided);
        printInfo.put("numberOfCopies", numberOfCopies);

        RestClient client = RestClient.builder()
                .baseUrl(this.url)
                .build();

        ResponseEntity<Void> response = client.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(printInfo)
                .retrieve()
                .toBodilessEntity();

        if (!response.getStatusCode()
                .is2xxSuccessful()) {

            throw new Exception("Failed to print document");

        }
    }
}
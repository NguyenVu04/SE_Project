package spss.project.backend.printer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
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
    @MongoId
    private String _id;

    /**
     * The URL of the printer's REST API.
     */
    @Indexed(name = "printer_url", unique = true)
    private String _url;

    /**
     * The model of the printer.
     */
    private String _model;

    /**
     * A description of the printer.
     */
    private String _description;

    /**
     * The name of the campus where the printer is located.
     */
    private String _campusName;

    /**
     * The name of the building where the printer is located.
     */
    private String _buildingName;

    /**
     * The room number where the printer is located.
     */
    private String _roomNumber;

    /**
     * Whether the printer is active or not.
     */
    private boolean _active;

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
            String _url,
            String _model,
            String _description,
            String _campusName,
            String _buildingName,
            String _roomNumber,
            boolean _active) {

        this._url = _url;
        this._model = _model;
        this._description = _description;
        this._campusName = _campusName;
        this._buildingName = _buildingName;
        this._roomNumber = _roomNumber;
        this._active = _active;
    }

    /**
     * Gets the id of the printer.
     * 
     * @return the id of the printer
     */
    public String getId() {
        return _id;
    }

    /**
     * Gets the URL of the printer.
     * 
     * @return the URL of the printer
     */
    public String getUrl() {
        return _url;
    }

    /**
     * Sets the URL of the printer.
     * 
     * @param url the URL to set
     */
    public void setUrl(String url) {
        this._url = url;
    }

    /**
     * Gets the model of the printer.
     * 
     * @return the model of the printer
     */
    public String getModel() {
        return _model;
    }

    /**
     * Sets the model of the printer.
     * 
     * @param model the model to set
     */
    public void setModel(String model) {
        this._model = model;
    }

    /**
     * Gets the description of the printer.
     * 
     * @return the description of the printer
     */
    public String getDescription() {
        return _description;
    }

    /**
     * Sets the description of the printer.
     * 
     * @param description the new description
     */
    public void setDescription(String description) {
        this._description = description;
    }

    /**
     * Gets the name of the campus where the printer is located.
     * 
     * @return the name of the campus
     */
    public String getCampusName() {
        return _campusName;
    }

    /**
     * Sets the name of the campus where the printer is located.
     * 
     * @param campusName the name of the campus
     */
    public void setCampusName(String campusName) {
        this._campusName = campusName;
    }

    /**
     * Gets the name of the building where the printer is located.
     * 
     * @return the name of the building
     */
    public String getBuildingName() {
        return _buildingName;
    }

    /**
     * Sets the name of the building where the printer is located.
     * 
     * @param buildingName the name of the building
     */
    public void setBuildingName(String buildingName) {
        this._buildingName = buildingName;
    }

    /**
     * Gets the room number where the printer is located.
     * 
     * @return the room number
     */
    public String getRoomNumber() {
        return _roomNumber;
    }

    /**
     * Sets the room number where the printer is located.
     * 
     * @param roomNumber the room number to set
     */
    public void setRoomNumber(String roomNumber) {
        this._roomNumber = roomNumber;
    }

    /**
     * Checks if the printer is active.
     *
     * @return true if the printer is active, false otherwise
     */
    public boolean isActive() {
        return _active;
    }

    /**
     * Sets the active status of the printer.
     * 
     * @param active the new active status
     */
    public void setActive(boolean active) {
        this._active = active;
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
                .baseUrl(this._url)
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
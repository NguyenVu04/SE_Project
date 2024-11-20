package spss.project.backend.print.printer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "printers")
public class Printer {
    @Id
    private String id;
    private String url;
    private String model;
    private String description;
    private String campusName;
    private String buildingName;
    private String roomNumber;
    private boolean active;

    public Printer(String url,
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

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    public boolean isActive() {
        return active;
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }
}
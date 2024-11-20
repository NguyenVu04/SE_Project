package spss.project.backend.configuration.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SystemConfig")
public class SystemConfig {
    @Id
    private String id;
    private int paperSupplyDay;
    private List<String> fileTypes;
    private Date createAt;
    private String createBy;

    public SystemConfig(int paperSupplyDay, String createBy) throws Exception {
        if (paperSupplyDay < 1 || paperSupplyDay > 28) {
            throw new Exception("Paper supply day must be between 1 and 28.");
        }

        this.paperSupplyDay = paperSupplyDay;
        fileTypes = new ArrayList<>();
        this.createAt = new Date();
        this.createBy = createBy;
    }

    public String getId() {
        return id;
    }

    public int getPaperSupplyDate() {
        return paperSupplyDay;
    }

    public void setPaperSupplyDate(int paperSupplyDate) throws Exception {
        if (paperSupplyDate < 0 || paperSupplyDate > 28) {
            throw new Exception("Paper supply date must be between 0 and 28.");
        }

        this.paperSupplyDay = paperSupplyDate;
    }

    public void addFileType(String fileType) {
        if (this.fileTypes.contains(fileType)) {
            return;
        }

        this.fileTypes.add(fileType);
    }

    public boolean hasFileType(String fileType) {
        return this.fileTypes.contains(fileType);
    }

    public boolean removeFileType(String fileType) {
        return this.fileTypes.remove(fileType);
    }

    public List<String> getFileTypes() {
        return List.copyOf(this.fileTypes);
    }

    public void setFileTypes(List<String> fileTypes) {
        this.fileTypes = new ArrayList<>(fileTypes);
    }

    public String getCreateBy() {
        return createBy;
    }

    public Date getCreateAt() {
        return createAt;
    }
}

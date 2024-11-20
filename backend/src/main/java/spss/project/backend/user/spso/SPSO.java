package spss.project.backend.user.spso;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SPSOs")
public class SPSO {
    @Id
    private String id;
    private String email;
}

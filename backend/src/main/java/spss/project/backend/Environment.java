package spss.project.backend;

/**
 * Contains constants and variables that are used throughout the project.
 */
public class Environment {
    /**
     * A protected constructor to prevent instantiation of the class.
     */
    private Environment() {
    }

    /**
     * The URL of the frontend.
     */
    public static final String FRONTEND_URL = "http://127.0.0.1:3000";

    /**
     * The character used to separate the student ID and the file name in a
     * GridFS file name.
     */
    public static final String FILE_SEPERATOR = "/";

    /**
     * The URL of the Cloud Server.
     */
    public static final String CLOUD_URL = "http://127.0.0.1:8080";
}

package spss.project.backend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Handles requests for general resources, such as the Javadocs for the API.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class GeneralController {
    /**
     * Default constructor for GeneralController.
     * This constructor is protected to prevent direct instantiation.
     */
    protected GeneralController() {}
    /**
     * Redirects to the Javadocs for the API.
     * 
     * @return a redirection to the Javadocs
     */
    @GetMapping("javadocs")
    public RedirectView getJavadocs() {
        return new RedirectView("./apidocs/index.html");
    }
}

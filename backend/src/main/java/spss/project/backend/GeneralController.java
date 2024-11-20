package spss.project.backend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class GeneralController {
    @GetMapping("javadocs")
    public RedirectView getJavadocs() {
        return new RedirectView("./site/apidocs/index.html");
    }
}

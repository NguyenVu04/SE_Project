package spss.project.backend.user.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spss.project.backend.Environment;

@RestController
@CrossOrigin(origins = {Environment.FRONTEND_URL})
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService service;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
}

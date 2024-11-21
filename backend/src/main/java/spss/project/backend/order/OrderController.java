package spss.project.backend.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spss.project.backend.Environment;

/**
 * Handles all requests related to orders.
 */
@RestController
@CrossOrigin(origins = {Environment.FRONTEND_URL})
@RequestMapping("order")
public class OrderController {

    /**
     * Creates a new order controller.
     */
    protected OrderController() {}

    /**
     * The service used to access and manipulate orders.
     */
    @Autowired
    private OrderService orderService;

    /**
     * Logger for log messages.
     */
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
}

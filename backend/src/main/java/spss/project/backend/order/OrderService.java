package spss.project.backend.order;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 * This class provides methods for working with the orders in the system
 */
@Service
public class OrderService {
    /**
     * Constructs a new order service. This constructor is protected to prevent
     * direct instantiation.
     */
    protected OrderService() {}

    /**
     * The repository for accessing and manipulating the orders.
     * 
     * @see OrderRepository
     */
    @Autowired
    private OrderRepository repo;

    /**
     * Saves a new order in the database.
     * 
     * @param studentId      the id of the student who submitted the order
     * @param printerId      the id of the printer on which the document was
     *                       printed
     * @param documentId     the id of the document which was printed
     * @param paperSize      the size of the paper the document was printed on
     * @param pageNumbers    the page numbers of the document which were printed
     * @param numberOfCopies the number of copies of the document which were
     *                       printed
     * @param singleSided     whether the document was printed single or double
     *                       sided
     * @param timeOrdered    the time at which the order was submitted
     * @param done           whether the order has been completed
     * @return the saved order
     * @throws Exception if an error occurs while saving the order
     */
    public Order save(
            String studentId,
            String printerId,
            String documentId,
            String paperSize,
            List<Integer> pageNumbers,
            int numberOfCopies,
            boolean singleSided,
            LocalDateTime timeOrdered,
            boolean done) throws Exception {

        return repo.save(new Order(
                studentId,
                printerId,
                documentId,
                paperSize,
                pageNumbers,
                numberOfCopies,
                singleSided,
                timeOrdered,
                done));
    }

    /**
     * Finds all orders which were submitted by the given student.
     * 
     * @param studentId the id of the student to find the orders for
     * @return the orders of the student, sorted by the time at which the
     *         orders were submitted in descending order
     * @throws Exception if an error occurs while retrieving the orders
     */
    public List<Order> getStudentOrders(String studentId) throws Exception {
        return repo.findByStudentId(
                studentId,
                Sort.by(Direction.DESC, "timeOrdered"));
    }

    /**
     * Finds an order by its id.
     * 
     * @param orderId the id of the order to find
     * @return the order with the given id, or null if no such order exists
     * @throws Exception if an error occurs while retrieving the order
     */
    public Order findOrder(String orderId) throws Exception {
        return repo.findById(orderId).orElse(null);
    }

    /**
     * Deletes an order by its id.
     * 
     * @param orderId the id of the order to delete
     * @throws Exception if an error occurs while deleting the order
     */
    public void deleteOrder(String orderId) throws Exception {
        repo.deleteById(orderId);
    }

    /**
     * Gets the repository for accessing and manipulating the orders.
     * 
     * @return the repository for the orders
     */
    public OrderRepository getRepo() {
        return repo;
    }
}

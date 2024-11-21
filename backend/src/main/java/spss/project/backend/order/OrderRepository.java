package spss.project.backend.order;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * A repository for accessing and manipulating orders in the database.
 */
public interface OrderRepository extends MongoRepository<Order, String> {

    /**
     * Finds all orders which were submitted by the given student.
     * 
     * @param studentId the id of the student to find the orders for
     * @param sort      the sort order of the orders
     * @return the orders of the student, sorted by the time at which the
     *         orders were submitted in descending order
     */
    public List<Order> findByStudentId(String studentId, Sort sort);
}

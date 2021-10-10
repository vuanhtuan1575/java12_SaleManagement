package java12.projectsalemanagement.order.repository;

import java12.projectsalemanagement.order.entiy.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

}

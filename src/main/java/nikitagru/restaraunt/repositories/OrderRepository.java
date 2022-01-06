package nikitagru.restaraunt.repositories;

import nikitagru.restaraunt.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByCustomerName(String customerName);
}

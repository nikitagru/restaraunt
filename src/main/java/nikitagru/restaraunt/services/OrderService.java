package nikitagru.restaraunt.services;

import nikitagru.restaraunt.entities.Order;
import nikitagru.restaraunt.repositories.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getByCustomerName(String customerName) {
        return orderRepository.findByCustomerName(customerName);
    }

    public void createNewOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}

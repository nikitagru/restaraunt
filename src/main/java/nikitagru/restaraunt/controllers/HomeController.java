package nikitagru.restaraunt.controllers;

import nikitagru.restaraunt.dto.OrderDto;
import nikitagru.restaraunt.entities.Order;
import nikitagru.restaraunt.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Controller
public class HomeController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        Date currentDate = new Date();
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String currentDateString = isoFormat.format(currentDate);

        model.addAttribute("currentDate", currentDateString);
        return "index";
    }

    @PostMapping("/order")
    public String createOrder(@ModelAttribute OrderDto orderDto) {
        Order savedOrder = orderService.getByCustomerName(orderDto.getCustomerName());

        if (savedOrder == null) {
            Order order = new Order();
            order.setCustomerName(orderDto.getCustomerName());
            order.setStartOrderDate(orderDto.getStartOrderDate());
            order.setEndOrderDate(orderDto.getEndOrderDate());
            orderService.createNewOrder(order);
        }

        return "index";
    }
}

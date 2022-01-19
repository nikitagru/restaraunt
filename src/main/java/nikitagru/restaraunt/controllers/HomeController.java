package nikitagru.restaraunt.controllers;

import nikitagru.restaraunt.checker.OrderChecker;
import nikitagru.restaraunt.dto.OrderDto;
import nikitagru.restaraunt.entities.Order;
import nikitagru.restaraunt.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
        SimpleDateFormat minFormat = new SimpleDateFormat("yyyy-MM-dd");
        String minDate = minFormat.format(currentDate);

        Date maxDay = new Date();
        SimpleDateFormat maxDayFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(maxDay);
        c.add(Calendar.DATE, 3);
        String maxDayTime = maxDayFormat.format(c.getTime());


        model.addAttribute("minDate", minDate);
        model.addAttribute("maxDay", maxDayTime);
        return "index";
    }

    @PostMapping("/order")
    public String createOrder(@ModelAttribute OrderDto orderDto, RedirectAttributes redirectAttributes) {
        Order savedOrder = orderService.getByCustomerName(orderDto.getCustomerName());
        OrderChecker orderChecker = new OrderChecker();

        if (savedOrder == null) {
            if (orderChecker.isExistFreeOrder(orderDto.getDate(), orderDto.getTime(), orderService.getAll())) {
                Order order = new Order();
                order.setCustomerName(orderDto.getCustomerName());
                order.setStartOrderDate(orderDto.getDate() + "T" + orderDto.getTime());
                order.setEndOrderDate(orderDto.getDate(), orderDto.getTime());
                orderService.createNewOrder(order);
            } else {
                redirectAttributes.addFlashAttribute("error", "Свободных мест на данное время и дату уже нет.");
            }
        } else {
            if (orderChecker.isOrdersInSameDay(savedOrder, orderDto.getDate())) { // заказы в разные дни в году
                redirectAttributes.addFlashAttribute("error", "У вас уже есть бронь.");
            } else {
                if (orderChecker.isExistFreeOrder(orderDto.getDate(), orderDto.getTime(), orderService.getAll())) {
                    Order order = new Order();
                    order.setCustomerName(orderDto.getCustomerName());
                    order.setStartOrderDate(orderDto.getDate() + "T" + orderDto.getTime());
                    order.setEndOrderDate(orderDto.getDate(), orderDto.getTime());
                    orderService.createNewOrder(order);
                } else {
                    redirectAttributes.addFlashAttribute("error", "Свободных мест на данное время и дату уже нет.");
                }
            }
        }

        return "redirect:/";
    }
}

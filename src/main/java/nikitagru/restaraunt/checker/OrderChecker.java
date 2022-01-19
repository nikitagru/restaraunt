package nikitagru.restaraunt.checker;

import nikitagru.restaraunt.entities.Order;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class OrderChecker {
    private final int maxOrders = 3;

    public boolean isExistFreeOrder(String inputStartDate, String inputTime, List<Order> orders) {
        int ordersCount = 0;
        Instant startDate = Instant.parse(inputStartDate + "T" + inputTime + ":00Z");
        Instant endDate = startDate.plus(2, ChronoUnit.HOURS);

        for (Order order : orders) {
            if (startDate.isAfter(Instant.parse(order.getStartOrderDate()))
                        && startDate.isBefore(Instant.parse(order.getEndOrderDate()))
                    || (endDate.isAfter(Instant.parse(order.getStartOrderDate()))
                        && endDate.isBefore(Instant.parse(order.getEndOrderDate())))
                    || (startDate.equals(Instant.parse(order.getStartOrderDate()))
                        && endDate.equals(Instant.parse(order.getEndOrderDate())))) {
                ordersCount++;
            }
        }

        return ordersCount < maxOrders;
    }

    public boolean isOrdersInSameDay(Order order, String inputStartDate) {
        String existedDay = order.getStartOrderDate().substring(0, 10);
        return existedDay.equals(inputStartDate);
    }
}

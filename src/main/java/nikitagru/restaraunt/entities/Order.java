package nikitagru.restaraunt.entities;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String startOrderDate;

    private String endOrderDate;

    private String code;

    public void setStartOrderDate(String startOrderDate) {
        this.startOrderDate = startOrderDate + ":00Z";
    }

    public void setEndOrderDate(String day, String startTime) {
        Instant startDate = Instant.parse(day + "T" + startTime + ":00Z");
        Instant endDate = startDate.plus(2, ChronoUnit.HOURS);

        this.endOrderDate = endDate.toString();
    }
}

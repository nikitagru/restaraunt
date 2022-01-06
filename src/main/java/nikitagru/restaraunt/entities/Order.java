package nikitagru.restaraunt.entities;

import lombok.Data;
import nikitagru.restaraunt.dto.OrderDto;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private Timestamp startOrderDate;

    private Timestamp endOrderDate;

    public void setStartOrderDate(String startOrderDate) {
        this.startOrderDate = Timestamp.valueOf(startOrderDate.replace('T', ' ') + ":00");
    }

    public void setEndOrderDate(String endOrderDate) {
        this.endOrderDate = Timestamp.valueOf(endOrderDate.replace('T', ' ') + ":00");
    }
}

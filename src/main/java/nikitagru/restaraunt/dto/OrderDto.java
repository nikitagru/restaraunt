package nikitagru.restaraunt.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String customerName;
    private String startOrderDate;
    private String endOrderDate;
}

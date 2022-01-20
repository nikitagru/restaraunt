package nikitagru.restaraunt.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String customerName;
    private String date;
    private String time;
    private String email;
}

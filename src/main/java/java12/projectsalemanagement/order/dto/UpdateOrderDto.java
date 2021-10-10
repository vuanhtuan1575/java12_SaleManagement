package java12.projectsalemanagement.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderDto {
    private Long id;
    private double price;
    private int quantity;


}

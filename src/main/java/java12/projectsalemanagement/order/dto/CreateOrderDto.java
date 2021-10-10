package java12.projectsalemanagement.order.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class CreateOrderDto {
    @Column
    private double price;
    @Column
    private int quantity;


}

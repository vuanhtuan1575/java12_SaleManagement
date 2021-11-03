package java12.projectsalemanagement.order.service;

import java12.projectsalemanagement.order.dto.AddProductDto;
import java12.projectsalemanagement.order.dto.CreateOrderDto;
import java12.projectsalemanagement.order.dto.UpdateOrderDto;
import java12.projectsalemanagement.order.entiy.Order;
import java12.projectsalemanagement.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<Order> findAll();

    Order addNewOrder(CreateOrderDto dto);

    void deleteById(Long orderId);

    Order update(UpdateOrderDto dto, Long id);

    Order addProduct(AddProductDto dto);
}

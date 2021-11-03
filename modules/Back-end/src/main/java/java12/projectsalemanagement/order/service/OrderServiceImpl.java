package java12.projectsalemanagement.order.service;


import java12.projectsalemanagement.order.dto.AddProductDto;
import java12.projectsalemanagement.order.dto.CreateOrderDto;
import java12.projectsalemanagement.order.dto.UpdateOrderDto;
import java12.projectsalemanagement.order.entiy.Order;
import java12.projectsalemanagement.order.repository.OrderRepository;
import java12.projectsalemanagement.product.entity.Product;
import java12.projectsalemanagement.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @Autowired
    public void OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order addNewOrder(CreateOrderDto dto) {
        Order newOrder = new Order();
        newOrder.setPrice(dto.getPrice());
        newOrder.setQuantity(dto.getQuantity());
        return orderRepository.save(newOrder);
    }

    @Override
    public void deleteById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order update(UpdateOrderDto dto, Long id) {
        Order updateOrder = orderRepository.getById(id);
        updateOrder.setPrice(dto.getPrice());
        updateOrder.setQuantity(dto.getQuantity());

        return orderRepository.save(updateOrder);
    }

    @Override
    public Order addProduct(AddProductDto dto) {
        Order order = orderRepository.getById(dto.getOrderId());
        Product product = productRepository.getById(dto.getProductId());
        order.addProduct(product);
        return orderRepository.save(order);
    }


}

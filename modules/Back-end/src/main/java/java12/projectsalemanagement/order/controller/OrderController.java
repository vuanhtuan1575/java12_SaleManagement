package java12.projectsalemanagement.order.controller;

import java12.projectsalemanagement.category.dto.CreateCategoryDto;
import java12.projectsalemanagement.category.dto.UpdateCategoryDto;
import java12.projectsalemanagement.category.entity.Category;
import java12.projectsalemanagement.category.service.CategoryService;
import java12.projectsalemanagement.common.util.ResponseHandler;
import java12.projectsalemanagement.order.dto.AddProductDto;
import java12.projectsalemanagement.order.dto.CreateOrderDto;
import java12.projectsalemanagement.order.dto.UpdateOrderDto;
import java12.projectsalemanagement.order.entiy.Order;
import java12.projectsalemanagement.order.service.OrderService;
import java12.projectsalemanagement.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderService service;

    @Autowired
    public OrderController(@Qualifier("orderServiceImpl") OrderService service) {
        this.service=service;
    }

    @GetMapping
    public Object findAllOrder(){
        List<Order> orders  =service.findAll();
        return ResponseHandler.getResponse(orders, HttpStatus.OK);

    }
    @PostMapping
    public Object addOrder(@Valid @RequestBody CreateOrderDto dto, BindingResult errors) {

        if (errors.hasErrors()) {

            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }

        Order addedOrder = service.addNewOrder(dto);


        return ResponseHandler.getResponse(addedOrder, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public Object deleteOrder(@PathVariable("id") Long orderId) {
        service.deleteById(orderId);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }


    @PutMapping
    public Object updateOrder(@Valid @RequestBody UpdateOrderDto dto,
                                 BindingResult errors) {
        if(errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Order updatedOrder = service.update(dto, dto.getId());
        return ResponseHandler.getResponse(updatedOrder, HttpStatus.OK);
    }

    // add Product into Order
    @PostMapping("/add-product")
    public Object addProduct(@Valid @RequestBody AddProductDto dto,
                             BindingResult errors) {
        if(errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Order updatedOrder = service.addProduct(dto);

        return ResponseHandler.getResponse(updatedOrder, HttpStatus.OK);
    }
}

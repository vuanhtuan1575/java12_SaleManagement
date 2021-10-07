package java12.projectsalemanagement.cart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @GetMapping("/cart")
    public Object welcome() {
        return "welcome to gira app";
    }
}

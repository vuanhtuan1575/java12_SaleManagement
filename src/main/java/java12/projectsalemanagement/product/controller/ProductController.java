package java12.projectsalemanagement.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping("/product")
    public Object welcome() {
        return "welcome to gira app";
    }
}

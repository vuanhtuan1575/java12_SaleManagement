package java12.projectsalemanagement.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @GetMapping("/234")
    public Object welcome() {
        return "welcome to gira app";
    }
}

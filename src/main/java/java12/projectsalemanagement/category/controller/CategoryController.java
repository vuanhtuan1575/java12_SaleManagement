package java12.projectsalemanagement.category.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @GetMapping("/category")
    public Object welcome() {
        return "welcome to gira app";
    }
}

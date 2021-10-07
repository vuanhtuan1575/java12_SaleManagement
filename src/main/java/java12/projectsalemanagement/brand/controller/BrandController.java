package java12.projectsalemanagement.brand.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrandController {
    @GetMapping("/brand")
    public Object welcome() {
        return "welcome to gira app";
    }
}

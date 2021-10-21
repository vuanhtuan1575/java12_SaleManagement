package java12.projectsalemanagement.user.controller;

import java12.projectsalemanagement.common.util.ResponseHandler;
import java12.projectsalemanagement.product.entity.Product;
import java12.projectsalemanagement.user.dto.AddRoleDto;
import java12.projectsalemanagement.user.dto.CreateUserDto;
import java12.projectsalemanagement.user.enitty.User;
import java12.projectsalemanagement.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    private  UserService service;

    @Autowired
    public UserController(@Qualifier("userServiceImpl") UserService service) {
        this.service = service;
    }
    @GetMapping("/getAll-user")
    public Object findAllUser() {
        List<User> users = service.findAll();
        return ResponseHandler.getResponse(users, HttpStatus.OK);
    }
    @PostMapping("/create-user")
    public Object createUser(@Valid @RequestBody CreateUserDto dto,
                             BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        User newUser = service.createUser(dto);

        return ResponseHandler.getResponse(newUser, HttpStatus.OK);
    }
    @PostMapping("/add-role")
    public Object addRoleToGroup(@Valid @RequestBody AddRoleDto dto, BindingResult errors) {
        if(errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        User updatedUser = service.addRole(dto);

        return ResponseHandler.getResponse(updatedUser, HttpStatus.OK);
    }
}

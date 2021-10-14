package java12.projectsalemanagement.user.controller;

import java12.projectsalemanagement.common.util.ResponseHandler;
import java12.projectsalemanagement.user.dto.CreateUserDto;
import java12.projectsalemanagement.user.enitty.User;
import java12.projectsalemanagement.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {

    private  UserService service;

    @Autowired
    public UserController(@Qualifier("userServiceImpl") UserService service) {
        this.service = service;
    }

    @PostMapping("/create-user")
    public Object createUser(@Valid @RequestBody CreateUserDto dto,
                             BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        User newUser = service.createUser(dto);

        return ResponseHandler.getResponse(newUser, HttpStatus.OK);
    }
}

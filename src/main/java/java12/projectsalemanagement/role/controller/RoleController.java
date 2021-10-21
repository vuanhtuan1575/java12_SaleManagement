package java12.projectsalemanagement.role.controller;

import java12.projectsalemanagement.common.util.ResponseHandler;
import java12.projectsalemanagement.role.dto.CreateRoleDto;
import java12.projectsalemanagement.role.entity.Role;
import java12.projectsalemanagement.role.service.RoleService;
import java12.projectsalemanagement.user.dto.CreateUserDto;
import java12.projectsalemanagement.user.enitty.User;
import java12.projectsalemanagement.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RoleController {
    private RoleService service;

    @Autowired
    public RoleController(@Qualifier("roleServiceImpl") RoleService service) {
        this.service = service;
    }
    
    @GetMapping("/getAll-role")
    public Object findAllUser() {
        List<Role> roles = service.findAll();
        return ResponseHandler.getResponse(roles, HttpStatus.OK);
    }
    @PostMapping("/create-role")
    public Object createRole(@Valid @RequestBody CreateRoleDto dto,
                             BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Role newRole = service.createRole(dto);

        return ResponseHandler.getResponse(newRole, HttpStatus.OK);
    }
}

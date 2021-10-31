package java12.projectsalemanagement.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java12.projectsalemanagement.common.util.ResponseHandler;
import java12.projectsalemanagement.user.dto.AddRoleDto;
import java12.projectsalemanagement.user.dto.CreateUserDto;
import java12.projectsalemanagement.user.enitty.User;
import java12.projectsalemanagement.user.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {

	private UserService service;

	@Autowired
	public UserController(@Qualifier("userServiceImpl") UserService service) {
		this.service = service;
	}

	@GetMapping
	@Secured("ROLE_ADMIN")
	public Object findAllUser() {
		List<User> users = service.findAll();
		return ResponseHandler.getResponse(users, HttpStatus.OK);
	}

	@PostMapping
	@Secured("ROLE_ADMIN")
	public Object createUser(@Valid @RequestBody CreateUserDto dto) {

		try {
			return service.createUser(dto);
		} catch (Exception e) { 
			Map<String, Object> map = new HashMap<>();
			map.put("statusCode", 500);
			map.put("message", "INTERNAL_SERVER_ERROR");
			return ResponseHandler.getResponse(map, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	
	@DeleteMapping(value = "/{id}")
	@Secured("ROLE_ADMIN")
	public Object deleteUser(@PathVariable long id) {
		
		return service.deleteUser(id);
	}
	
	@PostMapping("/add-role")
	@Secured("ROLE_ADMIN")
	public Object addRoleToGroup(@Valid @RequestBody AddRoleDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		User updatedUser = service.addRole(dto);

		return ResponseHandler.getResponse(updatedUser, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}")
	@Secured("ROLE_ADMIN")
	public Object findUserById(@PathVariable long id) {
		return service.findUserById(id);
	}
}

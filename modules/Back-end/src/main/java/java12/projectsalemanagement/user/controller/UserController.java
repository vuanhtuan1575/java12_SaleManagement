package java12.projectsalemanagement.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java12.projectsalemanagement.common.util.Constants;
import java12.projectsalemanagement.common.util.ResponseHandler;
import java12.projectsalemanagement.user.dto.CreateUserDto;
import java12.projectsalemanagement.user.dto.UpdateUserDto;
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
	public ResponseEntity<Object> findAllUser() {
		try {
			return service.findAll();
		} catch (Exception e) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, Constants.INTERNAL_SERVER_ERROR,
					false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCommon);
		}

	}

	@PostMapping
//	@Secured("ROLE_ADMIN")
	public ResponseEntity<Object> createUser(@Valid @RequestBody CreateUserDto dto) {

		try {
			return service.createUser(dto);
		} catch (Exception e) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, Constants.INTERNAL_SERVER_ERROR,
					false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCommon);

		}
	}

	@DeleteMapping(value = "/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Object> deleteUser(@PathVariable long id) {
		try {
			return service.deleteUser(id);
		} catch (Exception e) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, Constants.INTERNAL_SERVER_ERROR,
					false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCommon);

		}

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> findUserById(@PathVariable long id) {
		try {
			return service.findUserById(id);
		} catch (Exception e) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, Constants.INTERNAL_SERVER_ERROR,
					false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCommon);

		}

	}

	@PutMapping(value = "/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Object> updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody UpdateUserDto dto,
			BindingResult errors) {
		try {
			if (errors.hasErrors())
				return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

			return service.updateUser(id, dto);
		} catch (Exception e) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, Constants.INTERNAL_SERVER_ERROR,
					false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCommon);

		}
	}
/**
 * Deactive User
 * @param id
 * @return 
 */
	@GetMapping(value = "/deactive/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Object> deactiveUser(@PathVariable long id) {
		try {
			return service.deactiveUser(id);
		} catch (Exception e) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, Constants.INTERNAL_SERVER_ERROR,
					false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCommon);
		}

	}

	@GetMapping(value = "/active/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Object> activeUser(@PathVariable long id) {
		try {
			return service.activeUser(id);
		} catch (Exception e) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, Constants.INTERNAL_SERVER_ERROR,
					false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCommon);
		}

	}
	/**
	 * 
	 * @param id
	 * @param request
	 * @param valueCart = 0 Decrease or remove item in cart, valueCart = 1 Increase or add item to cart
	 * @return
	 */
	@GetMapping(value = "/add-to-cart/{productId}")
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public ResponseEntity<Object> addCart(@PathVariable(value = "productId", required = true) long id,
			HttpServletRequest request, @RequestParam int valueCart) {
		try {
			return service.addCart(id, request,valueCart);
		} catch (Exception e) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, Constants.INTERNAL_SERVER_ERROR,
					false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCommon);

		}

	}

}

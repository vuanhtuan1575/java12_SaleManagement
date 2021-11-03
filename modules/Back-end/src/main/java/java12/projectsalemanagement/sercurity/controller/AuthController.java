package java12.projectsalemanagement.sercurity.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import java12.projectsalemanagement.common.util.Constants;
import java12.projectsalemanagement.common.util.ResponseHandler;
import java12.projectsalemanagement.login.service.ServiceLogin;
import java12.projectsalemanagement.sercurity.dto.LoginDto;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(Jwts.class);

	private ServiceLogin serviceLogin;

	public AuthController(ServiceLogin serviceLogin) {
		this.serviceLogin = serviceLogin;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		try {

			return serviceLogin.checkLogin(dto);

		} catch (Exception e) {
			logger.debug("{} has been logged in with wrong password: {}", dto.getUsername(), e.getMessage());
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, Constants.INTERNAL_SERVER_ERROR,
					false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCommon);
		}
	}

	@GetMapping("/logout")
	public ResponseEntity<Object> getLogoutPage(HttpServletRequest request) {
		try {
			return serviceLogin.logout(request);
		} catch (Exception e) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, Constants.INTERNAL_SERVER_ERROR,
					false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCommon);
		}
	}

}

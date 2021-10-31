package java12.projectsalemanagement.login.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java12.projectsalemanagement.common.util.ResponseHandler;
import java12.projectsalemanagement.sercurity.dto.LoginDto;
import java12.projectsalemanagement.sercurity.jwt.JwtUtils;
import java12.projectsalemanagement.user.enitty.User;
import java12.projectsalemanagement.user.repository.UserRepository;

@Service
public class ServiceUserImpl implements ServiceLogin {

	private UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;

	@Autowired
	public ServiceUserImpl(UserRepository userRepository, AuthenticationManager authenticationManager,
			JwtUtils jwtUtils) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
	}

	@Override
	public ResponseEntity<Object> checkLogin(LoginDto loginDto) {
		Optional<User> opUser = userRepository.findByUsername(loginDto.getUsername());
		// check usser exist
		if (opUser.isEmpty()) {
			Map<String, Object> resError = new HashMap<>();
			resError.put("statusCode", "0000A1");
			resError.put("message", "Wrong Username or Password");
			resError.put("result", null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resError);

		}

		Authentication

		auth = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(auth);
		String token = jwtUtils.generateJwtToken(auth);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseHandler.ResponseCommon(200, "Login success", token));

	}

}

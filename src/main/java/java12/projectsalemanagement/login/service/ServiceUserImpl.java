package java12.projectsalemanagement.login.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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
import java12.projectsalemanagement.sercurity.entity.SessionManagerEntity;
import java12.projectsalemanagement.sercurity.jwt.JwtUtils;
import java12.projectsalemanagement.sercurity.repository.SessionManagerRepository;
import java12.projectsalemanagement.user.enitty.User;
import java12.projectsalemanagement.user.repository.UserRepository;

@Service
public class ServiceUserImpl implements ServiceLogin {

	private UserRepository userRepository;
	private SessionManagerRepository sessionManagerRepository;
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;

	@Autowired
	public ServiceUserImpl(UserRepository userRepository, AuthenticationManager authenticationManager,SessionManagerRepository sessionManagerRepository,
			JwtUtils jwtUtils) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
		this.sessionManagerRepository = sessionManagerRepository;
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
		addJwtToken(token, opUser.get().getUsername());
		return ResponseEntity.status(HttpStatus.OK).body(ResponseHandler.ResponseCommon(200, "Login success", token));

	}

	@Override
	public ResponseEntity<Object> logout(HttpServletRequest request) {
		String jwtTokenFromRequest = jwtUtils.getJwtTokenFromRequest(request);
		if(jwtTokenFromRequest == null) {
			Map<String, Object> responseUnauthor = ResponseHandler.ResponseCommon(401, "Token is invalid", false);
			ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseUnauthor);
		}
		
		Optional<SessionManagerEntity> optSession = sessionManagerRepository.findByToken(jwtTokenFromRequest);
		if(!optSession.isPresent()) {
			Map<String, Object> responseUnauthor = ResponseHandler.ResponseCommon(401, "Token is invalid", false);
			ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseUnauthor);
		}
		 sessionManagerRepository.delete(optSession.get());
		 Map<String, Object> response = ResponseHandler.ResponseCommon(200, "Logout is success", true);
			return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	private void addJwtToken(String token, String username) {
	    // add token to database
	    SessionManagerEntity entity = new SessionManagerEntity();
	    entity.setUserName(username);
	    entity.setToken(token);
	    entity.setCreatedBy(username);
	    entity.setUpdatedBy(username);
	    sessionManagerRepository.save(entity);
	}

}

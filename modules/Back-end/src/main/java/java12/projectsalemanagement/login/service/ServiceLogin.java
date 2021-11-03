package java12.projectsalemanagement.login.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import java12.projectsalemanagement.sercurity.dto.LoginDto;

public interface ServiceLogin {

	
	public ResponseEntity<Object> checkLogin(LoginDto loginDto);
	
	public ResponseEntity<Object> logout(HttpServletRequest request);
	
	
	
}

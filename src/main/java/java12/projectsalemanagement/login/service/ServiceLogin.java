package java12.projectsalemanagement.login.service;

import org.springframework.http.ResponseEntity;

import java12.projectsalemanagement.sercurity.dto.LoginDto;

public interface ServiceLogin {

	
	public ResponseEntity<Object> checkLogin(LoginDto loginDto);
	
	
}

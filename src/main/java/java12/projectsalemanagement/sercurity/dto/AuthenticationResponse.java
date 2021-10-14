package java12.projectsalemanagement.sercurity.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public class AuthenticationResponse {

	private String token;
	private String role;

	public AuthenticationResponse(String token, String role) {
		this.token = token;
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}

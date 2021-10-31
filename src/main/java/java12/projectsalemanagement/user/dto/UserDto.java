package java12.projectsalemanagement.user.dto;

import java12.projectsalemanagement.user.util.UserStatus;

public interface UserDto {
	public String getUsername();
	
	public String getEmail();

	public String getFullname();

	public String getAvatar();

	public UserStatus getStatus();

	public String getFacebook();




}

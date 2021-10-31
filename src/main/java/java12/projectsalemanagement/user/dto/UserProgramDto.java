package java12.projectsalemanagement.user.dto;

import java12.projectsalemanagement.role.util.HttpMethods;

public interface UserProgramDto {
	public String getName();
	public HttpMethods getMethod();
	public String getPath();
}

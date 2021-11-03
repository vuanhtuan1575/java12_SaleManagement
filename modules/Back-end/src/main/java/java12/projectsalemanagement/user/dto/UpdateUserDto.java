package java12.projectsalemanagement.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UpdateUserDto {

	private String password;
	@Email
	private String email;

	private String name;

	private String phone;

	@NotNull
	private Long[] roleIds;

}

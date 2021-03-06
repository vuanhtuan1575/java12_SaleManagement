package java12.projectsalemanagement.user.dto;

import java.time.LocalDateTime;
import java.util.Set;

import java12.projectsalemanagement.user.util.UserStatus;

public interface UserDto {

	public long getId();

	public String getCreatedBy();

	public LocalDateTime getCreateAt();

	public String getUpdatedBy();

	public LocalDateTime getUpdatedAt();

	public String getUsername();

	public String getEmail();

	public String getPhone();

	public String getName();

	public UserStatus getStatus();

	public Set<RoleDto> getRoles();

	public Set<Cart> getQuanty();

	interface RoleDto {
		public long getId();

		public String getName();
	}

	interface Cart {
		public Product getProduct();
		public int getQuanty();

		interface Product {
			public String getimageUlr();

			public Long getId();

			public String getName();

			public String getPrice();
		}

	}

}

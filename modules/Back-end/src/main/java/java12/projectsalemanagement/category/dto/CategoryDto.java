package java12.projectsalemanagement.category.dto;

import java.time.LocalDateTime;

public interface CategoryDto {
	public long getId();

	public String getName();

	public String getDescription();

	public String getImageUrl();

	public LocalDateTime getCreateAt();

	public String createBy();

	public String updateBy();
}

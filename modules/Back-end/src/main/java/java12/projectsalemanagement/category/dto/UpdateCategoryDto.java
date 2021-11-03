package java12.projectsalemanagement.category.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryDto {
	private String name;
	private String description;
	private String imageUrl;
	private Long[] brandIds;

	public UpdateCategoryDto(Long id, String name, String description, String imageUrl, Long[] brandIds) {
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.brandIds = brandIds;
	}

	public UpdateCategoryDto() {

	}
}

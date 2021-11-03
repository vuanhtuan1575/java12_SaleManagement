package java12.projectsalemanagement.brand.dto;

import java.util.List;

public interface BrandDto {

	// spring jpa projection
	
	public String getBrandName();

	public String getDescription();

	public String getUrlImage();

	public List<Category> getCategorys();

	interface Category {
		String getName();

		String getDescription();

		String getImageUrl();
	}

	public Long getId();

}

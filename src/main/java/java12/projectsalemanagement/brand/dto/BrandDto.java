package java12.projectsalemanagement.brand.dto;

import java.util.List;

import java12.projectsalemanagement.category.entity.Category;

public interface BrandDto {

	
	public String getBrandName();
	
	public void setBrandName();

	public String getDescription();

	public void setDescription();

	public String getUrlImage();

	public void setUrlImage();
	
	public List<Category> getCategorys();
	 
}

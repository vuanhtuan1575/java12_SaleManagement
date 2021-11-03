package java12.projectsalemanagement.product.dto;


public interface ProductDto {
	

	public String getDescription();

	public String getimageUlr();

	public Long getId();
	
	public String getName();
	
	public String getPrice();
	
	public String getTrademark();
	
	public BrandDto getBrand();
	
	public CategoryDto getCategory();
	
	interface BrandDto {
		public String getId();
		public String getBrandName();
	}
	interface CategoryDto {
		public String getId();
		public String getName();
	}
	
}

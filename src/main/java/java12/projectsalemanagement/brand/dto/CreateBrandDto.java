package java12.projectsalemanagement.brand.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CreateBrandDto {

	 @NotBlank
	 private String brandName;
	
	 private String description;
	 @NotBlank
	 private String urlImage;
	 
	 @NotBlank
	 private Long[] idCategorys;

	
	 
	 	
	 
}

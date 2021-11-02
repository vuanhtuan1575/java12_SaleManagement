package java12.projectsalemanagement.brand.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UpdateBrandDto {
		
	private String name;
	
	private String description;
	
	private String urlImage;
	
	private Long[] categoryIds;
}

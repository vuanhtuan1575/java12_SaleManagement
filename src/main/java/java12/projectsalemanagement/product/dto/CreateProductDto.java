package java12.projectsalemanagement.product.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateProductDto {
	@NotNull
	private String name;

	private String imageUlr;

	private String description;
	private String review;
	private double price;
	private String trademark;
	private Long brandId;

}

package java12.projectsalemanagement.product.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UpdateProductDto {
    @NotNull
    @Column
    private String name;

    @Column
    private String imageUlr;

    @Column
    private String description;
    @Column
    private String review;
    @Column
    private double price;
    @Column
    private String trademark;
    
    private Long categoryId;
    private Long brandId;

}

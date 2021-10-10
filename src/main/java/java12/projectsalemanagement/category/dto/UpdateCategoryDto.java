package java12.projectsalemanagement.category.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryDto {
    private Long id;
    private String name;
    private String description;
    private String imageUlr;

    public UpdateCategoryDto(Long id, String name, String description, String imageUlr) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUlr = imageUlr;
    }
}

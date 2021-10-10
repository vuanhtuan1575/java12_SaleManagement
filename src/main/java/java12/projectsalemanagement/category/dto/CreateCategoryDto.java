package java12.projectsalemanagement.category.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryDto {
    private String name;
    private String description;

    public CreateCategoryDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

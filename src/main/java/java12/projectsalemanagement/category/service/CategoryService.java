package java12.projectsalemanagement.category.service;

import java12.projectsalemanagement.category.dto.CreateCategoryDto;
import java12.projectsalemanagement.category.dto.UpdateCategoryDto;
import java12.projectsalemanagement.category.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> findAll();

    Category addNewCategory(CreateCategoryDto dto);

    void deleteById(Long categoryId);

    Category update(UpdateCategoryDto dto, Long id);
}

package java12.projectsalemanagement.category.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java12.projectsalemanagement.category.dto.CreateCategoryDto;
import java12.projectsalemanagement.category.dto.UpdateCategoryDto;
import java12.projectsalemanagement.category.entity.Category;

@Service
public interface CategoryService {
	 ResponseEntity<Object> findAll();

    ResponseEntity<Object> addNewCategory(CreateCategoryDto dto);

    void deleteById(Long categoryId);

    Category update(UpdateCategoryDto dto, Long id);
}

package java12.projectsalemanagement.category.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java12.projectsalemanagement.category.dto.CreateCategoryDto;
import java12.projectsalemanagement.category.dto.UpdateCategoryDto;

@Service
public interface CategoryService {
	ResponseEntity<Object> findAll();

    ResponseEntity<Object> addNewCategory(CreateCategoryDto dto);

    ResponseEntity<Object> deleteCategory(Long categoryId);

    ResponseEntity<Object> updateCategory(long id, UpdateCategoryDto dto);
    
    ResponseEntity<Object> findById(long id);
}

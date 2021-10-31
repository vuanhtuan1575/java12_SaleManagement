package java12.projectsalemanagement.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java12.projectsalemanagement.category.dto.CreateCategoryDto;
import java12.projectsalemanagement.category.dto.UpdateCategoryDto;
import java12.projectsalemanagement.category.entity.Category;
import java12.projectsalemanagement.category.repository.CategoryRepository;
import java12.projectsalemanagement.common.util.ResponseHandler;

@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryRepository categoryRepository;

	@Autowired
	public void ProductServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public ResponseEntity<Object> findAll() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseHandler.ResponseCommon(200, "Get all category success", categoryRepository.findAll()));
	}

	@Override
	public ResponseEntity<Object> addNewCategory(CreateCategoryDto dto) {

		Category newCategories = new Category();
		newCategories.setName(dto.getName());
		newCategories.setDescription(dto.getDescription());
		Category save = categoryRepository.save(newCategories);

		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseHandler.ResponseCommon(200, "Category create success", save));
	}

	@Override
	public void deleteById(Long categoryId) {
		categoryRepository.deleteById(categoryId);
	}

	@Override
	public Category update(UpdateCategoryDto dto, Long id) {
		Category updateCategory = categoryRepository.getById(id);
		updateCategory.setName(dto.getName());
		updateCategory.setDescription(dto.getDescription());
		updateCategory.setImageUlr(dto.getImageUlr());
		return categoryRepository.save(updateCategory);
	}
}

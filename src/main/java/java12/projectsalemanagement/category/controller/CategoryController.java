package java12.projectsalemanagement.category.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java12.projectsalemanagement.category.dto.CreateCategoryDto;
import java12.projectsalemanagement.category.dto.UpdateCategoryDto;
import java12.projectsalemanagement.category.entity.Category;
import java12.projectsalemanagement.category.service.CategoryService;
import java12.projectsalemanagement.common.util.ResponseHandler;

@RestController
@RequestMapping("api/category")
public class CategoryController {
	private CategoryService service;

	@Autowired
	public CategoryController(@Qualifier("categoryServiceImpl") CategoryService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<Object> findAllCategory() {
		try {
			return service.findAll();
		} catch (Exception e) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, "INTERNAL_SERVER_ERROR", null);
			return ResponseEntity.status(HttpStatus.OK).body(responseCommon);
		}

	}

	@PostMapping
	@Secured("ROLE_ADMIN")
	public Object addCategory(@Valid @RequestBody CreateCategoryDto dto, BindingResult errors) {
		try {

			if (errors.hasErrors()) {

				return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
			}

			return service.addNewCategory(dto);
		} catch (Exception e) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, "INTERNAL_SERVER_ERROR", null);
			return ResponseEntity.status(HttpStatus.OK).body(responseCommon);
		}

	}

	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public Object deleteCategory(@PathVariable("id") Long categoryId) {
		service.deleteById(categoryId);
		return ResponseHandler.getResponse(HttpStatus.OK);
	}

	@PutMapping
	@Secured("ROLE_ADMIN")
	public Object updateCategory(@Valid @RequestBody UpdateCategoryDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Category updatedCategory = service.update(dto, dto.getId());
		return ResponseHandler.getResponse(updatedCategory, HttpStatus.OK);
	}
}

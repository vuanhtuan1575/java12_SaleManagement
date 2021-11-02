package java12.projectsalemanagement.category.controller;

import java.util.HashMap;
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
	public ResponseEntity<Object> addCategory(@Valid @RequestBody CreateCategoryDto dto, BindingResult errors) {
		try {

			if (errors.hasErrors()) {

				return service.addNewCategory(dto);
			}

			return service.addNewCategory(dto);
		} catch (Exception e) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, "INTERNAL_SERVER_ERROR", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCommon);
		}

	}

	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public Object deleteCategory(@PathVariable("id") Long categoryId) {
		try {
			return service.deleteCategory(categoryId);
		} catch (Exception e) {
		}
		Map<String, Object> map = new HashMap<>();
		map.put("statusCode", 500);
		map.put("message", "INTERNAL_SERVER_ERROR");
		return ResponseHandler.getResponse(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Object> updateCategory(@PathVariable Long id,@Valid @RequestBody UpdateCategoryDto dto) {
		try {
			return service.updateCategory(id, dto);
		} catch (Exception e) {

			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, "INTERNAL_SERVER_ERROR", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCommon);
		}
	}

}

package java12.projectsalemanagement.product.controller;

import java.util.Map;

import javax.validation.Valid;

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

import java12.projectsalemanagement.common.util.Constants;
import java12.projectsalemanagement.common.util.ResponseHandler;
import java12.projectsalemanagement.product.dto.CreateProductDto;
import java12.projectsalemanagement.product.dto.UpdateProductDto;
import java12.projectsalemanagement.product.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	private ProductService service;

	public ProductController(@Qualifier("productServiceImpl") ProductService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<Object> findAllProduct() {
		try {
			return service.findAll();
		} catch (Exception e) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, Constants.INTERNAL_SERVER_ERROR, false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCommon);

		}
	}

	@PostMapping
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Object> addProduct(@Valid @RequestBody CreateProductDto dto, BindingResult errors) {
		try {
			if (errors.hasErrors()) {

				return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

			}
			return service.addNewProduct(dto);
		} catch (Exception e) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, Constants.INTERNAL_SERVER_ERROR, false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCommon);
		}

	}

	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long productId) {
		try {
			return service.deleteById(productId);
		} catch (Exception e) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, Constants.INTERNAL_SERVER_ERROR, false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCommon);
		}

	}

	@PutMapping(value = "/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") Long id ,@Valid @RequestBody UpdateProductDto dto, BindingResult errors) {
		try {
			if (errors.hasErrors())
				return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

			return service.updateProduct(id, dto);
		}catch (Exception e) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, Constants.INTERNAL_SERVER_ERROR, false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCommon);
		}
		
	}

	@GetMapping("/{productId}")
	public Object findByProductId(@PathVariable("productId") Long productId) {

		try {
			return service.findProductById(productId);
		} catch (Exception e) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, Constants.INTERNAL_SERVER_ERROR, false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCommon);
		}

	}

}

package java12.projectsalemanagement.product.service;

import org.springframework.http.ResponseEntity;

import java12.projectsalemanagement.product.dto.CreateProductDto;
import java12.projectsalemanagement.product.dto.UpdateProductDto;

public interface ProductService {
	ResponseEntity<Object> findAll();

	ResponseEntity<Object> addNewProduct(CreateProductDto dto);

	ResponseEntity<Object> deleteById(Long productId);

	ResponseEntity<Object> updateProduct(Long id, UpdateProductDto dto);

	ResponseEntity<Object> findProductById(long id);

}

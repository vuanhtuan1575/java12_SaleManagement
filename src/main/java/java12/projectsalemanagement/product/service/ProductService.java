package java12.projectsalemanagement.product.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import java12.projectsalemanagement.product.dto.CreateProductDto;
import java12.projectsalemanagement.product.dto.UpdateProductDto;
import java12.projectsalemanagement.product.entity.Product;

public interface ProductService {
	ResponseEntity<Object> findAll();

   ResponseEntity<Object> addNewProduct(CreateProductDto dto);

   ResponseEntity<Object> deleteById(Long productId);

    Product update(UpdateProductDto dto, Long id);

    Product findProductById(Long productId);

    List<Product> findProductByName(String productName);


    //Product findProductByName(String product);
}

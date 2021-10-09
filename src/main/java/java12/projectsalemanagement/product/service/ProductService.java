package java12.projectsalemanagement.product.service;

import java12.projectsalemanagement.product.dto.CreateProductDto;
import java12.projectsalemanagement.product.dto.UpdateProductDto;
import java12.projectsalemanagement.product.entity.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Qualifier("productService")
@Service
public interface ProductService {
    List<Product> findAll();

   Product addNewProduct(CreateProductDto dto);

    void deleteById(Long productId);

    Product update(UpdateProductDto dto, Long id);




}

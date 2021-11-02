package java12.projectsalemanagement.product.repository;

import java12.projectsalemanagement.product.dto.ProductDto;
import java12.projectsalemanagement.product.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

Product findProductById(Long productId);


@Query("SELECT p FROM Product p WHERE id=?1")
public Optional<ProductDto> findProductById(long id);

}

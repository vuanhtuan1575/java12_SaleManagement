package java12.projectsalemanagement.product.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import java12.projectsalemanagement.product.entity.Product;
import java12.projectsalemanagement.product.entity.ProductUser;
import java12.projectsalemanagement.user.enitty.User;

public interface ProductUserRepository extends CrudRepository<ProductUser, Long> {

	public Optional<ProductUser> findByProductAndUser(Product product,User user);
}

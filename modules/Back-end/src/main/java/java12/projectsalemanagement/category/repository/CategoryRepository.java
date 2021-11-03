package java12.projectsalemanagement.category.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java12.projectsalemanagement.category.dto.CategoryDto;
import java12.projectsalemanagement.category.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query("SELECT c FROM Category c WHERE id=?1")
	public Optional<CategoryDto> findCategoryById(long id);

	public List<CategoryDto> findBy();

}

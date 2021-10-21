package java12.projectsalemanagement.brand.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java12.projectsalemanagement.brand.dto.BrandDto;
import java12.projectsalemanagement.brand.entity.Brand;
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

	@Query("SELECT b FROM Brand b")
	public List<BrandDto> findAllBrandDto();
	@Query("SELECT b FROM Brand b WHERE id=?1")
	public Optional<BrandDto> findBrandById(long id);
}

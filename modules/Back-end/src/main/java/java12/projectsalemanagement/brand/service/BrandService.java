package java12.projectsalemanagement.brand.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java12.projectsalemanagement.brand.dto.CreateBrandDto;
import java12.projectsalemanagement.brand.dto.UpdateBrandDto;

@Service
public interface BrandService {

	  ResponseEntity<Object> createBrand(CreateBrandDto dto);
	  
	  ResponseEntity<Object> findAllDto();
	  
	  ResponseEntity<Object> deleteBrand(long brandId);
	  
	  ResponseEntity<Object> findBrandById(long id);
	  
	  ResponseEntity<Object> updateBrand(long id, UpdateBrandDto dto);
}

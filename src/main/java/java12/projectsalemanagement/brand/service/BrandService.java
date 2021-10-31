package java12.projectsalemanagement.brand.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java12.projectsalemanagement.brand.dto.BrandDto;
import java12.projectsalemanagement.brand.dto.CreateBrandDto;

@Service
public interface BrandService {

	  ResponseEntity<Object> createBrand(CreateBrandDto dto);
	  
	  ResponseEntity<Object> findAllDto();
	  
	  ResponseEntity<Object> deleteBrand(long brandId);
	  
	  Object findBrandById(long id);
}

package java12.projectsalemanagement.brand.service;

import java.util.List;

import org.springframework.stereotype.Service;

import java12.projectsalemanagement.brand.dto.BrandDto;
import java12.projectsalemanagement.brand.dto.CreateBrandDto;
import java12.projectsalemanagement.brand.entity.Brand;

@Service
public interface BrandService {

	  Brand createBrand(CreateBrandDto dto);
	  List<BrandDto> findAllDto();
	  Object deleteBrand(long id);
	  Object findBrandById(long id);
}

package java12.projectsalemanagement.brand.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java12.projectsalemanagement.brand.dto.BrandDto;
import java12.projectsalemanagement.brand.dto.CreateBrandDto;
import java12.projectsalemanagement.brand.entity.Brand;
import java12.projectsalemanagement.brand.repository.BrandRepository;
import java12.projectsalemanagement.category.entity.Category;
import java12.projectsalemanagement.category.repository.CategoryRepository;
import java12.projectsalemanagement.common.util.ResponseHandler;

@Service
public class BrandServiceImpl implements BrandService {
	private BrandRepository repository;
	private CategoryRepository categoryRepository;

	@Autowired
	public BrandServiceImpl(CategoryRepository categoryRepository, BrandRepository brandRepository) {
		this.categoryRepository = categoryRepository;
		this.repository = brandRepository;
		// encoder = passwordEncoder;
	}

	@Override
	public ResponseEntity<Object> createBrand(CreateBrandDto dto) {
		// container category;
		Set<Category> setCategory = new HashSet<>();
		// Check Category Exist

		Long[] idCategorys = dto.getIdCategorys();
		for (Long id : idCategorys) {
			Optional<Category> opCategoty = categoryRepository.findById(id);
			if (opCategoty.isEmpty()) {
				Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(404, "Category is not exist",
						false);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
			}
			setCategory.add(opCategoty.get());
		}
		// Check container category must be not empty
		if (setCategory.isEmpty()) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(404, "Category is not exist", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
		}

		Brand newBrand = new Brand();
		newBrand.setBrandName(dto.getBrandName());
		newBrand.setDescription(dto.getDescription());
		newBrand.setUrlImage(dto.getUrlImage());
		newBrand.setCategorys(setCategory);
		Brand save = repository.save(newBrand);

		Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(200, "Brand create is success", save);

		return ResponseEntity.status(HttpStatus.OK).body(responseCommon);
	}

	@Override
	public ResponseEntity<Object> findAllDto() {
		 Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(200, "Find Brand success", repository.findAllBrandDto());
		 return ResponseEntity.status(HttpStatus.OK).body(responseCommon);

	}

	@Override
	public ResponseEntity<Object> deleteBrand(long brandId) {
		Optional<Brand> opBrand = repository.findById(brandId);
		if (opBrand.isEmpty()) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(404, "Brand is not exist", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
		}
		repository.deleteById(brandId);
		Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(200, "Brand delete is success", true);
		return ResponseEntity.status(HttpStatus.OK).body(responseCommon);
	}

	@Override
	public Object findBrandById(long id) {

		Optional<BrandDto> opBrandDto = repository.findBrandById(id);
		if (opBrandDto.isEmpty()) {
			return ResponseHandler.getErrors("Brand not exist", HttpStatus.NOT_FOUND);
		}
		return ResponseHandler.getResponse(opBrandDto.get(), HttpStatus.OK);

	}

}

package java12.projectsalemanagement.brand.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java12.projectsalemanagement.brand.dto.BrandDto;
import java12.projectsalemanagement.brand.dto.CreateBrandDto;
import java12.projectsalemanagement.brand.dto.UpdateBrandDto;
import java12.projectsalemanagement.brand.entity.Brand;
import java12.projectsalemanagement.brand.repository.BrandRepository;
import java12.projectsalemanagement.category.entity.Category;
import java12.projectsalemanagement.category.repository.CategoryRepository;
import java12.projectsalemanagement.common.util.DateUtils;
import java12.projectsalemanagement.common.util.ResponseHandler;

@Service
public class BrandServiceImpl implements BrandService {
	private BrandRepository repository;
	private CategoryRepository categoryRepository;

	@Autowired
	public BrandServiceImpl(CategoryRepository categoryRepository, BrandRepository brandRepository) {
		this.categoryRepository = categoryRepository;
		this.repository = brandRepository;
		// encoder = passwordEncoder;m
	}

	@Override
	public ResponseEntity<Object> createBrand(CreateBrandDto dto) {
		// container category;
		Set<Category> setCategory = new HashSet<>();
		// Check Category Exist

		Long[] idCategorys = dto.getIdCategorys();
		for (Long id : idCategorys) {
			Optional<Category> opCategoty = categoryRepository.findById(id);
			if (!opCategoty.isPresent()) {
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
		newBrand.setCreateAt(DateUtils.getLocalDateNow());
		newBrand.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		Brand save = repository.save(newBrand);

		Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(200, "Brand create is success", save);

		return ResponseEntity.status(HttpStatus.OK).body(responseCommon);
	}

	@Override
	public ResponseEntity<Object> findAllDto() {
		List<BrandDto> optList = repository.findAllBrandDto();
		return ResponseEntity.status(HttpStatus.OK).body(optList);
	}

	@Override
	public ResponseEntity<Object> deleteBrand(long brandId) {
		Optional<Brand> opBrand = repository.findById(brandId);
		if (!opBrand.isPresent()) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(404, "Brand is not exist", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
		}
		repository.deleteById(brandId);
		Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(200, "Brand delete is success", true);
		return ResponseEntity.status(HttpStatus.OK).body(responseCommon);
	}

	@Override
	public ResponseEntity<Object> findBrandById(long id) {
		Optional<BrandDto> opBrand = repository.findBrandById(id);
		if (!opBrand.isPresent()) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(404, "Brand is not exist", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
		}
		Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(200, "Brand find success", opBrand.get());
		return ResponseEntity.status(HttpStatus.OK).body(responseCommon);

	}

	
	
	/**
	 * Update Brand
	 * 
	 * @param
	 * 	Long Id
	 * 	UpdateBrandDto dto
	 */
	@Override
	public ResponseEntity<Object> updateBrand(long id, UpdateBrandDto dto) {
		
		Optional<Brand> opBrand = repository.findById(id);
		if(!opBrand.isPresent()) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(400, "Brand not exist", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
			
		}
		Set<Category> categoryList = new HashSet<>();
		if(dto.getCategoryIds()!= null && dto.getCategoryIds().length>=1) {
			for (Long idCategoty : dto.getCategoryIds()) {
				Optional<Category> opCategoty = categoryRepository.findById(idCategoty);
				if(!opCategoty.isPresent()) {
					Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(400, "Category not exist", false);
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
				} else {
					categoryList.add(opCategoty.get());
				}
			}
		}
		if(dto.getName() != null && !StringUtils.isBlank(dto.getName())) {
			opBrand.get().setBrandName(dto.getName());
		}
		if(dto.getDescription() != null && !StringUtils.isBlank(dto.getDescription())) {
			opBrand.get().setDescription(dto.getDescription());
		}
		if(dto.getUrlImage() != null && !StringUtils.isBlank(dto.getUrlImage())) {
			opBrand.get().setUrlImage(dto.getUrlImage());
		}
		if(!categoryList.isEmpty()) {
			opBrand.get().setCategorys(categoryList);
		}
		Brand save = repository.save(opBrand.get());
		Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(200, "Brand update is success", save);
		return ResponseEntity.status(HttpStatus.OK).body(responseCommon);
		
		
		
		
		
		
	}
	
	

}

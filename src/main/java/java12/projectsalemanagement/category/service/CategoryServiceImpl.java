package java12.projectsalemanagement.category.service;

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

import java12.projectsalemanagement.brand.entity.Brand;
import java12.projectsalemanagement.brand.repository.BrandRepository;
import java12.projectsalemanagement.category.dto.CreateCategoryDto;
import java12.projectsalemanagement.category.dto.UpdateCategoryDto;
import java12.projectsalemanagement.category.entity.Category;
import java12.projectsalemanagement.category.repository.CategoryRepository;
import java12.projectsalemanagement.common.util.DateUtils;
import java12.projectsalemanagement.common.util.ResponseHandler;

@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryRepository categoryRepository;
	private BrandRepository brandCategory;

	@Autowired
	public void ProductServiceImpl(CategoryRepository categoryRepository, BrandRepository brandCategory) {
		this.categoryRepository = categoryRepository;
		this.brandCategory = brandCategory;
	}

	@Override
	public ResponseEntity<Object> findAll() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(categoryRepository.findBy());
	}

	@Override
	public ResponseEntity<Object> addNewCategory(CreateCategoryDto dto) {
		Category newCategories = new Category();
		newCategories.setName(dto.getName());
		newCategories.setDescription(dto.getDescription());
		newCategories.setCreateAt(DateUtils.getLocalDateNow());
		newCategories.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		Category save = categoryRepository.save(newCategories);

		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseHandler.ResponseCommon(200, "Category create success", save));
	}

	@Override
	public ResponseEntity<Object> deleteCategory(Long categoryId) {
		Optional<Category> opCategory = categoryRepository.findById(categoryId);
		if (opCategory.isEmpty()) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(404, "Category is not exist", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
		}
		// Remove category of brand
		List<Brand> listBrand = brandCategory.findByCategorys(opCategory.get());
		for (Brand brand : listBrand) {
			brand.getCategorys().remove(opCategory.get());
		}
		categoryRepository.deleteById(categoryId);
		Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(200, "Category delete is success", true);
		return ResponseEntity.status(HttpStatus.OK).body(responseCommon);
	}

	@Override
	public ResponseEntity<Object> updateCategory(long id, UpdateCategoryDto dto) {
		Optional<Category> opCategory = categoryRepository.findById(id);
		if (opCategory.isEmpty()) {
			ResponseHandler.ResponseCommon(400, "Category is not exist", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(ResponseHandler.ResponseCommon(400, "Category is not exist", false));
		}
		Set<Brand> brandSet = new HashSet<>();
		if (dto.getBrandIds() != null && dto.getBrandIds().length >= 1) {
			for (Long idCategoty : dto.getBrandIds()) {
				Optional<Brand> opBrand = brandCategory.findById(idCategoty);
				if (opBrand.isEmpty()) {
					Map<String, Object> responseError = ResponseHandler.ResponseCommon(400, "Brand is not exist",
							false);
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
				} else {
					brandSet.add(opBrand.get());
				}

			}
		}
		if (dto.getName() != null && !StringUtils.isBlank(dto.getName())) {
			opCategory.get().setName(dto.getName());
		}
		if (dto.getDescription() != null && !StringUtils.isBlank(dto.getDescription())) {
			opCategory.get().setDescription(dto.getDescription());
		}
		if (dto.getImageUrl() != null && !StringUtils.isBlank(dto.getImageUrl())) {
			opCategory.get().setImageUrl(dto.getImageUrl());
		}
		if (!brandSet.isEmpty()) {
			opCategory.get().setBrands(brandSet);
		}
		Category save = categoryRepository.save(opCategory.get());
		Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(200, "Update Category Success", save);
		return ResponseEntity.status(HttpStatus.OK).body(responseCommon);
	}
}

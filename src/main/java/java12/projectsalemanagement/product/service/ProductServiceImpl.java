package java12.projectsalemanagement.product.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java12.projectsalemanagement.brand.entity.Brand;
import java12.projectsalemanagement.brand.repository.BrandRepository;
import java12.projectsalemanagement.category.entity.Category;
import java12.projectsalemanagement.category.repository.CategoryRepository;
import java12.projectsalemanagement.common.util.DateUtils;
import java12.projectsalemanagement.common.util.ResponseHandler;
import java12.projectsalemanagement.product.dto.CreateProductDto;
import java12.projectsalemanagement.product.dto.ProductDto;
import java12.projectsalemanagement.product.dto.UpdateProductDto;
import java12.projectsalemanagement.product.entity.Product;
import java12.projectsalemanagement.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	private BrandRepository brandRepository;
	private CategoryRepository categoryRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, BrandRepository brandRepository,
			CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.brandRepository = brandRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public ResponseEntity<Object> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(productRepository.findBy());
	}

	@Override
	public ResponseEntity<Object> addNewProduct(CreateProductDto dto) {

		Optional<Brand> optBrand = brandRepository.findById(dto.getBrandId());
		Optional<Category> opCategory = categoryRepository.findById(dto.getCategoryId());

		if (optBrand.isEmpty()) {
			return ResponseEntity.status(400).body(ResponseHandler.ResponseCommon(400, "Brand is not exist", false));
		}
		if (opCategory.isEmpty()) {
			return ResponseEntity.status(400).body(ResponseHandler.ResponseCommon(404, "Category is not exist", false));
		}

		Product newProduct = new Product();
		newProduct.setName(dto.getName());
		newProduct.setDescription(dto.getDescription());
		newProduct.setImageUlr(dto.getImageUlr());
		newProduct.setReview(dto.getReview());
		newProduct.setPrice(dto.getPrice());
		newProduct.setTrademark(dto.getTrademark());
		newProduct.setBrand(optBrand.get());
		newProduct.setCategory(opCategory.get());
		newProduct.setCreateAt(DateUtils.getLocalDateNow());
		newProduct.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		Product save = productRepository.save(newProduct);

		Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(200, "create product success", save);
		return ResponseEntity.status(HttpStatus.OK).body(responseCommon);
	}

	@Override
	public ResponseEntity<Object> deleteById(Long productId) {

		Optional<Product> opPro = productRepository.findById(productId);
		// Check product is exist
		if (opPro.isEmpty()) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(404, "Product is not exist", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
		}
		productRepository.deleteById(productId);
		Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(200, "Product delete success", true);
		return ResponseEntity.status(HttpStatus.OK).body(responseCommon);
	}

	@Override
	public ResponseEntity<Object> updateProduct(Long id, UpdateProductDto dto) {
		Optional<Product> opProduct = productRepository.findById(id);
		if (opProduct.isEmpty()) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(400, "Product not exist", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
		}
		if (dto.getCategoryId() != null) {
			Optional<Category> opCategory = categoryRepository.findById(dto.getCategoryId());
			if (opCategory.isEmpty()) {
				Map<String, Object> responseError = ResponseHandler.ResponseCommon(400, "Category not exist", false);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
			}
			opProduct.get().setCategory(opCategory.get());
		}

		if (dto.getBrandId() != null) {
			Optional<Brand> optBrand = brandRepository.findById(dto.getBrandId());
			if (optBrand.isEmpty()) {
				Map<String, Object> responseError = ResponseHandler.ResponseCommon(400, "Brand not exist", false);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
			}
			opProduct.get().setBrand(optBrand.get());
		}

		opProduct.get().setDescription(dto.getDescription());
		opProduct.get().setImageUlr(dto.getImageUlr());
		opProduct.get().setName(dto.getName());
		opProduct.get().setPrice(dto.getPrice());
		opProduct.get().setTrademark(dto.getTrademark());
		opProduct.get().setReview(dto.getReview());
		opProduct.get().setVersion(opProduct.get().getVersion() + 1);
		opProduct.get().setUpdatedAt(DateUtils.getLocalDateNow());
		opProduct.get().setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		Product productSave = productRepository.save(opProduct.get());
		Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(200, "Product update success", productSave);
		return ResponseEntity.status(HttpStatus.OK).body(responseCommon);

	}

	@Override
	public ResponseEntity<Object> findProductById(long id) {
		Optional<ProductDto> opProduct = productRepository.findProductById(id);
		if (opProduct.isEmpty()) {
			Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(404, "Product is not exist", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCommon);
		}
		Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(200, "Product find success",
				opProduct.get());
		return ResponseEntity.status(HttpStatus.OK).body(responseCommon);
	}

}

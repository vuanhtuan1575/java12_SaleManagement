package java12.projectsalemanagement.product.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java12.projectsalemanagement.brand.entity.Brand;
import java12.projectsalemanagement.brand.repository.BrandRepository;
import java12.projectsalemanagement.common.util.ResponseHandler;
import java12.projectsalemanagement.product.dto.CreateProductDto;
import java12.projectsalemanagement.product.dto.UpdateProductDto;
import java12.projectsalemanagement.product.entity.Product;
import java12.projectsalemanagement.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	private ProductRepository productRepository;
	private BrandRepository brandRepository;

	@Autowired
	public void ProductServiceImpl(ProductRepository productRepository, BrandRepository brandRepository) {
		this.productRepository = productRepository;
		this.brandRepository = brandRepository;
	}

	@Override
	public ResponseEntity<Object> findAll() {
		List<Product> listProduct = productRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseHandler.ResponseCommon(200, "Get all product success", listProduct));

	}

	@Override
	public ResponseEntity<Object> addNewProduct(CreateProductDto dto) {

		Optional<Brand> optBrand = brandRepository.findById(dto.getBrandId());

		if (optBrand.isEmpty()) {
			return ResponseEntity.status(400)
					.body(ResponseHandler.ResponseCommon(404, "Brand is not exist", optBrand.get()));
		}

		Product newProduct = new Product();
		newProduct.setName(dto.getName());
		newProduct.setDescription(dto.getDescription());
		newProduct.setImageUlr(dto.getImageUlr());
		newProduct.setReview(dto.getReview());
		newProduct.setPrice(dto.getPrice());
		newProduct.setTrademark(dto.getTrademark());
		newProduct.setBrand(optBrand.get());
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
	public Product update(UpdateProductDto dto, Long id) {
		Product updateProduct = productRepository.getById(id);
		updateProduct.setName(dto.getName());
		updateProduct.setDescription(dto.getDescription());
		updateProduct.setImageUlr(dto.getImageUlr());
		updateProduct.setReview(dto.getReview());
		updateProduct.setPrice(dto.getPrice());
		updateProduct.setTrademark(dto.getTrademark());
		return productRepository.save(updateProduct);

	}

	@Override
	public Product findProductById(Long productId) {
		return productRepository.findProductById(productId);
	}

	@Override
	public List<Product> findProductByName(String productName) {
		return productRepository.findProductsByName(productName);
	}

//    @Override
//    public List<Product> findProductById(Long productId) {
//        return productRepository.findProductById(productId);
//    }
//
//    @Override
//    public List<Product> findProductByName(String productName) {
//        return productRepository.findProductByName(productName);
//    }

//    @Override
//    public Product findProductByName(String product) {
//        return pro=productRepository.findProductByName(product);
//    }

}

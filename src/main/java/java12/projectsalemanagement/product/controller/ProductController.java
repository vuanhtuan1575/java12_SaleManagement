package java12.projectsalemanagement.product.controller;

import java12.projectsalemanagement.common.util.ResponseHandler;
import java12.projectsalemanagement.product.dto.CreateProductDto;
import java12.projectsalemanagement.product.dto.UpdateProductDto;
import java12.projectsalemanagement.product.entity.Product;
import java12.projectsalemanagement.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	private ProductService service;

	public ProductController(@Qualifier("productServiceImpl") ProductService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<Object> findAllProduct() {
		try {
			return service.findAll();
		} catch (Exception e) {
			Map<String, Object> map = new HashMap<>();
			map.put("statusCode", 500);
			map.put("message", "INTERNAL_SERVER_ERROR");
			return ResponseHandler.getResponse(map, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PostMapping
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Object> addProduct(@Valid @RequestBody CreateProductDto dto, BindingResult errors) {
		try {
			if (errors.hasErrors()) {

				return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

			}
			return service.addNewProduct(dto);
		} catch (Exception e) {
			Map<String, Object> map = new HashMap<>();
			map.put("statusCode", 500);
			map.put("message", "INTERNAL_SERVER_ERROR");
			return ResponseHandler.getResponse(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long productId) {
		try {
			return service.deleteById(productId);
		} catch (Exception e) {
			Map<String, Object> map = new HashMap<>();
			map.put("statusCode", 500);
			map.put("message", "INTERNAL_SERVER_ERROR");
			return ResponseHandler.getResponse(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping
	@Secured("ROLE_ADMIN")
	public Object updateProduct(@Valid @RequestBody UpdateProductDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		Product updatedProduct = service.update(dto, dto.getId());
		return ResponseHandler.getResponse(updatedProduct, HttpStatus.OK);
	}

	// add product into Order
//    @PostMapping("/add-Order")
//    public Object addProgram(@Valid @RequestBody AddDto dto,
//                             BindingResult errors) {
//        if(errors.hasErrors())
//            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
//
//        Role updatedRole = service.addProgram(dto);
//
//        return ResponseHandler.getResponse(updatedRole, HttpStatus.OK);
//    }

	@GetMapping("/product-id/{productId}")
	public Object findByProductId(@PathVariable("productId") Long productId) {
		Product productToFind = service.findProductById(productId);

		return ResponseHandler.getResponse(productToFind, HttpStatus.OK);
	}
//    @GetMapping("/{productName}")

	@GetMapping("/product-name/{productName}")
	public Object findByProductName(@PathVariable("productName") String productName) {
		List<Product> productToFind = service.findProductByName(productName);

		return ResponseHandler.getResponse(productToFind, HttpStatus.OK);
	}
}

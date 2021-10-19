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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService service;

    public ProductController(@Qualifier("productServiceImpl") ProductService service) {
        this.service = service;
    }

        @GetMapping
        public Object findAllProduct() {
            List<Product> products = service.findAll();
            return ResponseHandler.getResponse(products, HttpStatus.OK);
        }

    @PostMapping
    public Object addProduct(@Valid @RequestBody CreateProductDto dto, BindingResult errors) {

        if (errors.hasErrors()) {

            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }

        Product addedProduct = service.addNewProduct(dto);


        return ResponseHandler.getResponse(addedProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public Object deleteProduct(@PathVariable("id")Long productId) {
        service.deleteById(productId);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }

    @PutMapping
    public Object updateProduct(@Valid @RequestBody UpdateProductDto dto,
                                BindingResult errors) {
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

        return ResponseHandler.getResponse(productToFind,HttpStatus.OK);
    }
//    @GetMapping("/{productName}")




    @GetMapping("/product-name/{productName}")
    public Object findByProductName(@PathVariable("productName") String productName) {
        List<Product> productToFind =  service.findProductByName(productName);

        return ResponseHandler.getResponse(productToFind,HttpStatus.OK);
    }
}

package java12.projectsalemanagement.product.service;

import java12.projectsalemanagement.product.dto.CreateProductDto;
import java12.projectsalemanagement.product.dto.UpdateProductDto;
import java12.projectsalemanagement.product.entity.Product;
import java12.projectsalemanagement.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    @Autowired
    public void ProductServiceImpl(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product addNewProduct(CreateProductDto dto) {
        Product newProduct = new Product();
        newProduct.setName(dto.getName());
        newProduct.setDescription(dto.getDescription());
        newProduct.setImageUlr(dto.getImageUlr());
        newProduct.setReview(dto.getReview());
        newProduct.setPrice(dto.getPrice());
        newProduct.setTrademark(dto.getTrademark());
        return productRepository.save(newProduct);
    }

    @Override
    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
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

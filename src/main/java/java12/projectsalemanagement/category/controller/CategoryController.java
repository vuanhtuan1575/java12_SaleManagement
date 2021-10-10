package java12.projectsalemanagement.category.controller;

import java12.projectsalemanagement.category.dto.CreateCategoryDto;
import java12.projectsalemanagement.category.dto.UpdateCategoryDto;
import java12.projectsalemanagement.category.entity.Category;
import java12.projectsalemanagement.category.service.CategoryService;
import java12.projectsalemanagement.common.util.ResponseHandler;
import java12.projectsalemanagement.product.dto.CreateProductDto;
import java12.projectsalemanagement.product.dto.UpdateProductDto;
import java12.projectsalemanagement.product.entity.Product;
import java12.projectsalemanagement.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    private CategoryService service;

    @Autowired
    public CategoryController(@Qualifier("categoryServiceImpl") CategoryService service) {
        this.service=service;
    }

    @GetMapping
    public Object findAllCategory(){
        List<Category> categories  =service.findAll();
        return ResponseHandler.getResponse(categories, HttpStatus.OK);

    }
    @PostMapping
    public Object addCategory(@Valid @RequestBody CreateCategoryDto dto, BindingResult errors) {

        if (errors.hasErrors()) {

            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }

        Category addedCategory = service.addNewCategory(dto);


        return ResponseHandler.getResponse(addedCategory, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public Object deleteCategory(@PathVariable("id") Long categoryId) {
        service.deleteById(categoryId);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }
    @PutMapping
    public Object updateCategory(@Valid @RequestBody UpdateCategoryDto dto,
                                BindingResult errors) {
        if(errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Category updatedCategory = service.update(dto, dto.getId());
        return ResponseHandler.getResponse(updatedCategory, HttpStatus.OK);
    }
}

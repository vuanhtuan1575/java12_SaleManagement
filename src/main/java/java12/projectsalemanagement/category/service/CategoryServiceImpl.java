package java12.projectsalemanagement.category.service;

import java12.projectsalemanagement.category.dto.CreateCategoryDto;
import java12.projectsalemanagement.category.dto.UpdateCategoryDto;
import java12.projectsalemanagement.category.entity.Category;
import java12.projectsalemanagement.category.repository.CategoryRepository;
import java12.projectsalemanagement.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;
    @Autowired
    public void ProductServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addNewCategory(CreateCategoryDto dto) {
        Category newCategories = new Category();
        newCategories.setName(dto.getName());
        newCategories.setDescription(dto.getDescription());
        return categoryRepository.save(newCategories);
    }

    @Override
    public void deleteById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Category update(UpdateCategoryDto dto, Long id) {
        Category updateCategory = categoryRepository.getById(id);
        updateCategory.setName(dto.getName());
        updateCategory.setDescription(dto.getDescription());
        updateCategory.setImageUlr(dto.getImageUlr());
        return categoryRepository.save(updateCategory);
    }
}

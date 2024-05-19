package com.bookstore.bookstore.Service.BookAndCategoryServiceImpl;

import com.bookstore.bookstore.DTO.BookDTO;
import com.bookstore.bookstore.DTO.CategoryDTO;
import com.bookstore.bookstore.Entity.Category;
import com.bookstore.bookstore.Repository.CategoryRepository;
import com.bookstore.bookstore.Service.BookAndCategoryService.CategoryService;
import com.bookstore.bookstore.Service.DTO_Service.BookMapper;
import com.bookstore.bookstore.Service.DTO_Service.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> findAll() {

        List<Category> categoryList = categoryRepository.findAll();

        return categoryList.stream().map(categoryMapper::toCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Long id) {

        Category category = categoryRepository.findById(id).orElse(null);

        return categoryMapper.toCategoryDTO(category);
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {

        Category category = categoryMapper.toCategory(categoryDTO);
        category = categoryRepository.save(category);
        return categoryMapper.toCategoryDTO(category);
    }

    @Override
    public void delete(Long id) {

        Category category = categoryRepository.findById(id).orElse(null);

    }

    @Override
    public List<BookDTO> findBooksByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return category.getBooklist().stream().map(bookMapper::toBookDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findByName(String categoryName) {

        Category category = categoryRepository.findByName(categoryName);
        if(category == null){
            return null;
        }
        return categoryMapper.toCategoryDTO(category);
    }
}

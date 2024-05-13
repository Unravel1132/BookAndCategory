package com.bookstore.bookstore.Service.BookAndCategoryService;

import com.bookstore.bookstore.DTO.CategoryDTO;
import com.bookstore.bookstore.Entity.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> findAll();
    CategoryDTO findById(Long id);
    CategoryDTO save(CategoryDTO categoryDTO);
    void delete(Long id);

    CategoryDTO findByName(String categoryName);
}

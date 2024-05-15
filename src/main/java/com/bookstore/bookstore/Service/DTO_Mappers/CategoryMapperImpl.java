package com.bookstore.bookstore.Service.DTO_Mappers;


import com.bookstore.bookstore.DTO.CategoryDTO;
import com.bookstore.bookstore.Entity.Category;
import com.bookstore.bookstore.Service.DTO_Service.CategoryMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public Category toCategory(CategoryDTO categoryDTO) {

        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }

    @Override
    public CategoryDTO toCategoryDTO(Category category) {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }
}

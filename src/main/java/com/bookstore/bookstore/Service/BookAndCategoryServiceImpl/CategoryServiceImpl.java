package com.bookstore.bookstore.Service.BookAndCategoryServiceImpl;


import com.bookstore.bookstore.DTO.BookDTO;
import com.bookstore.bookstore.DTO.CategoryDTO;
import com.bookstore.bookstore.Entity.Book;
import com.bookstore.bookstore.Entity.Category;
import com.bookstore.bookstore.Repository.BookRepository;
import com.bookstore.bookstore.Repository.CategoryRepository;
import com.bookstore.bookstore.Service.BookAndCategoryService.CategoryService;
import com.bookstore.bookstore.Service.DTO_Service.BookMapper;
import com.bookstore.bookstore.Service.DTO_Service.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper, BookMapper bookMapper, BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> category = categoryRepository.findAll();
        return category.stream().map(categoryMapper::toCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            Category categoryEntity = category.get();
            return categoryMapper.toCategoryDTO(categoryEntity);
        }
        return null;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {

        Category category = categoryMapper.toCategory(categoryDTO);
        Category savedCategory = categoryRepository.save(category);

        return  categoryMapper.toCategoryDTO(savedCategory);
    }

    @Override
    public void delete(Long id) {
        if(id != null) {
            categoryRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("id can't be null");
        }
    }

    @Override
    public List<BookDTO> findBooksByCategoryId(Long categoryId) {
        if (categoryId != null) {
            List<Book> books = bookRepository.findByCategoryId(categoryId);
            return books.stream()
                    .map(bookMapper::toBookDTO)
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Category id cannot be null");
        }
    }

    @Override
    public CategoryDTO findByName(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        if(category != null) {
            return categoryMapper.toCategoryDTO(category);
        }
        else {
            return null;
        }
    }
}

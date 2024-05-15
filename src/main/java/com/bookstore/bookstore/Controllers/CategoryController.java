package com.bookstore.bookstore.Controllers;


import com.bookstore.bookstore.DTO.BookDTO;
import com.bookstore.bookstore.DTO.CategoryDTO;
import com.bookstore.bookstore.Service.BookAndCategoryService.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2")
public class CategoryController {

    private final CategoryService categoryService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategory() {

        try {
            return ResponseEntity.ok(categoryService.findAll());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {

        try {
            return ResponseEntity.ok(categoryService.save(categoryDTO));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        try {
            CategoryDTO categoryDTO = categoryService.findById(id);
            if (categoryDTO != null) {
                List<BookDTO> bookDTOS = categoryService.findBooksByCategoryId(id);
                categoryDTO.setBooklist(bookDTOS);
                return ResponseEntity.ok(categoryDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
        try {
            if (categoryService.findById(id) != null) {
                categoryService.delete(id);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<CategoryDTO> finByName(@RequestParam String name) {
        try{
            return ResponseEntity.ok(categoryService.findByName(name));
        }catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

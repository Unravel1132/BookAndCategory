package com.bookstore.bookstore.Controllers;


import com.bookstore.bookstore.DTO.BookDTO;
import com.bookstore.bookstore.Service.BookAndCategoryServiceImpl.CategoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/v2")
public class CategoryController {

    private final CategoryServiceImpl categoryServiceimpl;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    @Autowired
    public CategoryController(CategoryServiceImpl categoryServiceimpl) {
        this.categoryServiceimpl = categoryServiceimpl;
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<BookDTO>> getCategoryById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(categoryServiceimpl.findBooksByCategoryId(id));

        }catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }

    }

}

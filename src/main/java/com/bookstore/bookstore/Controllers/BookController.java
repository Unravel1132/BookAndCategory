package com.bookstore.bookstore.Controllers;


import com.bookstore.bookstore.DTO.BookDTO;
import com.bookstore.bookstore.Entity.Book;
import com.bookstore.bookstore.Service.BookAndCategoryServiceImpl.BookServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")

public class BookController {

    private final BookServiceImpl bookService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        try{
            return ResponseEntity.ok(bookService.getBooks());
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Нет книг", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @PostMapping("/add")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {


    }
}

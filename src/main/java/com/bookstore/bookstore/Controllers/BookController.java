package com.bookstore.bookstore.Controllers;


import com.bookstore.bookstore.DTO.BookDTO;
import com.bookstore.bookstore.Service.BookAndCategoryService.BookService;
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


    private final BookServiceImpl bookServiceImpl;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);


    @Autowired
    public BookController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }


    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        try{
           return ResponseEntity.ok(bookServiceImpl.getBooks());

        }catch (Exception e){
            logger.error("Не удалось найти список книг", e);
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/add/{categoryId}")
    public ResponseEntity<BookDTO> addBook(@PathVariable Long categoryId, @RequestBody BookDTO bookDTO) {
        try {
            BookDTO newBook = bookServiceImpl.addBook(bookDTO, categoryId);
            return new ResponseEntity<>(newBook, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Ошибка при добавлении книги: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/put/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long bookId,
                                              @RequestBody BookDTO bookDTO) {

        try{
            return ResponseEntity.ok(bookServiceImpl.updateBook(bookDTO, bookId));
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

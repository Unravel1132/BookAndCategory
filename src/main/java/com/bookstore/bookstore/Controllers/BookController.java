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
        try {
            return ResponseEntity.ok(bookService.getBooks());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Нет книг", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<BookDTO> addBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        try {
            return ResponseEntity.ok(bookService.addBook(bookDTO, id));
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long bookId,
                                              @RequestBody BookDTO bookDTO,
                                              @RequestParam(name = "categoryId", required = false) Long categoryId) {
        try {
            BookDTO updatedBook = bookService.updateBook(bookDTO, categoryId, bookId);
            return ResponseEntity.ok(updatedBook);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/all/books")
    public ResponseEntity<List<BookDTO>> getBooksByCategory(@RequestParam String categoryName) {
        try {
            List<BookDTO> books = bookService.getBooksByCategoryName(categoryName);
            if (books.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            logger.error("An error occurred while fetching books by category: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
           BookDTO bookDTO = bookService.getBookById(id);
           if (bookDTO == null) {
               return ResponseEntity.notFound().build();
           }
           else {
               bookService.deleteBook(bookDTO.getId());
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.noContent().build();
    }

}

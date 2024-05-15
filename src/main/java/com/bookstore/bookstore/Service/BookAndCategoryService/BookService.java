package com.bookstore.bookstore.Service.BookAndCategoryService;

import com.bookstore.bookstore.DTO.BookDTO;
import com.bookstore.bookstore.Entity.Book;

import java.util.List;

public interface BookService {

    List<BookDTO> getBooks();
    BookDTO getBookById(Long id);
    BookDTO addBook(BookDTO bookDTO, Long categoryId);
    BookDTO updateBook(BookDTO bookDTO, Long categoryId, Long bookId);
    List<BookDTO> getBooksByCategoryName(String categoryName);
    void deleteBook(Long id);
}

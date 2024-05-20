package com.bookstore.bookstore;


import com.bookstore.bookstore.Controllers.BookController;
import com.bookstore.bookstore.DTO.BookDTO;
import com.bookstore.bookstore.DTO.CategoryDTO;
import com.bookstore.bookstore.Entity.Book;
import com.bookstore.bookstore.Entity.Category;
import com.bookstore.bookstore.Service.BookAndCategoryServiceImpl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookTestController {

    @Mock
    BookServiceImpl bookService;

    @InjectMocks
    BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private List<BookDTO> books;


    private List<CategoryDTO> categories;

    @Test
    void findBooks() {

        //when

        when(bookService.getBooks()).thenReturn(books);

        ResponseEntity<List<BookDTO>> response = bookController.getAllBooks();


        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), books);


    }

    @Test
    void findBookWithExeption() {

        when(bookService.getBooks()).thenThrow(new RuntimeException("НЕ найдено"));


        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> bookController.getAllBooks());

        assertEquals("Не найдено", runtimeException.getMessage());

    }


    @Test
    void findBookById() {

        Long id = 1L;

        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor("Author");
        bookDTO.setPrice(23.43);

        when(bookService.getBookById(id)).thenReturn(bookDTO);

        ResponseEntity<BookDTO> bookDTOResponseEntity = bookController.getBookById(id);

        assertEquals(bookDTOResponseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(bookDTOResponseEntity.getBody(), bookDTO);

        verify(bookService, times(1)).getBookById(id);

    }

    @Test
    void findBookByIdException() {
        Long id = 1L;

        when(bookService.getBookById(id)).thenReturn(null);

        ResponseEntity<BookDTO> bookDTOResponseEntity = bookController.getBookById(id);

        assertEquals(HttpStatus.NOT_FOUND, bookDTOResponseEntity.getStatusCode());
        assertNull(bookDTOResponseEntity.getBody());


    }


    @Test
    void saveBook() {

        Long categoryId = 1L;
        BookDTO bookDTO = new BookDTO();

        BookDTO savedBookDTO = new BookDTO();
        savedBookDTO.setAuthor("Author");
        savedBookDTO.setPrice(23.43);


        when(bookService.addBook(bookDTO, categoryId)).thenReturn(savedBookDTO);


        ResponseEntity<BookDTO> bookDTOResponseEntity = bookController.addBook(categoryId, bookDTO);

        assertEquals(bookDTOResponseEntity.getStatusCode(), HttpStatus.CREATED);
        assertEquals(bookDTOResponseEntity.getBody(), savedBookDTO);

        verify(bookService, times(1)).addBook(bookDTO, categoryId);


    }

    @Test
    void saveBookException() {

        Long categoryId = 1L;


        BookDTO bookDTO = new BookDTO();
        when(bookService.addBook(bookDTO, categoryId)).thenThrow(new RuntimeException("Error"));

        Exception runtimeException = assertThrows(RuntimeException.class, ()->
                bookController.addBook(categoryId, bookDTO));

        assertEquals("Eror", runtimeException.getMessage());
    }
}

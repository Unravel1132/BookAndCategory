package com.bookstore.bookstore;


import com.bookstore.bookstore.Controllers.BookController;
import com.bookstore.bookstore.DTO.BookDTO;
import com.bookstore.bookstore.Entity.Book;
import com.bookstore.bookstore.Service.BookAndCategoryServiceImpl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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


    @Test
    void findBooks(){

        //when

        when (bookService.getBooks()).thenReturn(books);

        ResponseEntity<List<BookDTO>> response = bookController.getAllBooks();


        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), books);





    }

}

package com.bookstore.bookstore.Service.DTO_Mappers;


import com.bookstore.bookstore.DTO.BookDTO;
import com.bookstore.bookstore.Entity.Book;
import com.bookstore.bookstore.Service.DTO_Service.BookMapper;
import org.springframework.stereotype.Service;

@Service
public class BookMapperImpl implements BookMapper {


    @Override
    public Book toBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());

        return book;
    }

    @Override
    public BookDTO toBookDTO(Book book) {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPrice(book.getPrice());

        return bookDTO;
    }
}

package com.bookstore.bookstore.Service.DTO_Service;

import com.bookstore.bookstore.DTO.BookDTO;
import com.bookstore.bookstore.Entity.Book;

public interface BookMapper {

    Book toBook(BookDTO bookDTO);
    BookDTO toBookDTO(Book book);
}

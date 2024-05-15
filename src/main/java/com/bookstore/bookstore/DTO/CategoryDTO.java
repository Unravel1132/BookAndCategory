package com.bookstore.bookstore.DTO;


import com.bookstore.bookstore.Entity.Book;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
   private List<BookDTO> booklist;
}

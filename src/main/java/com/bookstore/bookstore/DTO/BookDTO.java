package com.bookstore.bookstore.DTO;


import com.bookstore.bookstore.Entity.Category;
import lombok.Data;

@Data
public class BookDTO {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Double price;


}

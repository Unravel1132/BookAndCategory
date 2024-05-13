package com.bookstore.bookstore.Repository;

import com.bookstore.bookstore.DTO.CategoryDTO;
import com.bookstore.bookstore.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByCategory(CategoryDTO categoryDTO);
}

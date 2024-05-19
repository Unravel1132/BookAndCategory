package com.bookstore.bookstore.Repository;

import com.bookstore.bookstore.DTO.CategoryDTO;
import com.bookstore.bookstore.Entity.Book;
import com.bookstore.bookstore.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    List<Book> findByCategory(Category category);

    @Query("SELECT b FROM Book b WHERE b.category.name = :categoryName")
    List<Book> findByCategoryName(@Param("categoryName") String categoryName);

}

package com.bookstore.bookstore.Repository;

import com.bookstore.bookstore.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

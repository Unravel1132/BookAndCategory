package com.bookstore.bookstore.Service.BookAndCategoryServiceImpl;


import com.bookstore.bookstore.DTO.BookDTO;
import com.bookstore.bookstore.DTO.CategoryDTO;
import com.bookstore.bookstore.Entity.Book;
import com.bookstore.bookstore.Entity.Category;
import com.bookstore.bookstore.Repository.BookRepository;
import com.bookstore.bookstore.Service.BookAndCategoryService.BookService;
import com.bookstore.bookstore.Service.BookAndCategoryService.CategoryService;
import com.bookstore.bookstore.Service.DTO_Service.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {



    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.categoryService = categoryService;
    }

    @Override
    public List<BookDTO> getBooks() {
        List<Book> bookDTOList = bookRepository.findAll();
        return bookDTOList.stream()
                .map(bookMapper::toBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {

        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Book book1 = book.get();
            return bookMapper.toBookDTO(book1);
        }
        return null;
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO, Long categoryId) {

        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());

        Category category = new Category();
        if (categoryId == null) {
            throw new IllegalArgumentException("Category id cannot be null");
        }
        category.setId(categoryId);
        book.setCategory(category);
        bookRepository.save(book);
        return bookMapper.toBookDTO(book);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO, Long categoryId, Long bookId){
        if(bookId != null){
            Optional<Book> book = bookRepository.findById(bookId);
            if (book.isPresent()) {
                Book book1 = book.get();
                book1.setTitle(bookDTO.getTitle());
                book1.setAuthor(bookDTO.getAuthor());
                book1.setPrice(bookDTO.getPrice());
                Category category = new Category();
                if(categoryId != null){
                    category.setId(categoryId);
                    book1.setCategory(category);
                }
                else {
                    throw new IllegalArgumentException("Category id cannot be null");
                }
                bookRepository.save(book1);
                return bookMapper.toBookDTO(book1);

            }
            else {
                throw new IllegalArgumentException("Book id cannot be null");
            }
        }else {
            throw new IllegalArgumentException("Book id cannot be null");
        }
    }


    @Override
    public List<BookDTO> getBooksByCategoryName(String categoryName) {
        CategoryDTO categoryDTO = categoryService.findByName(categoryName);
        if(categoryDTO != null) {
            List<Book> bookDTOS = bookRepository.findByCategory(categoryDTO);
            return bookDTOS.stream().map(bookMapper::toBookDTO)
                    .collect(Collectors.toList());
        }
        else {
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteBook(Long id) {
        if(id != null) {
             bookRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("Book id cannot be null");
        }
     }
}

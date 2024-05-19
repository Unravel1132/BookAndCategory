package com.bookstore.bookstore.Service.BookAndCategoryServiceImpl;

import com.bookstore.bookstore.DTO.BookDTO;
import com.bookstore.bookstore.Entity.Book;
import com.bookstore.bookstore.Entity.Category;
import com.bookstore.bookstore.Repository.BookRepository;
import com.bookstore.bookstore.Repository.CategoryRepository;
import com.bookstore.bookstore.Service.BookAndCategoryService.BookService;
import com.bookstore.bookstore.Service.DTO_Mappers.BookMapperImpl;
import com.bookstore.bookstore.Service.DTO_Service.BookMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<BookDTO> getBooks() {

        List<Book> books = bookRepository.findAll();
        return books.stream().map(bookMapper::toBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {

        Optional<Book> book = bookRepository.findById(id);

        return book.map(bookMapper::toBookDTO).orElse(null);
    }

    @Override
    @Transactional
    public BookDTO addBook(BookDTO bookDTO, Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Такой категории не существует!"));

        Book book = bookMapper.toBook(bookDTO);
        book.setCategory(category);


        return bookMapper.toBookDTO(bookRepository.save(book));
    }

    @Override
    @Transactional
    public BookDTO updateBook(BookDTO bookDTO, Long bookId) {

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Not found"));
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        return bookMapper.toBookDTO(bookRepository.save(book));

    }

    @Override
    public List<BookDTO> getBooksByCategoryName(String categoryName) {

        List<Book> book = bookRepository.findByCategoryName(categoryName);


        return book.stream().map(bookMapper::toBookDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteBook(Long id) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        bookRepository.delete(book);

    }
}

package com.example.bookstore.controller;

import com.example.bookstore.dto.book.BookResponse;
import com.example.bookstore.entities.Book;
import com.example.bookstore.exceptions.BookNotFoundException;
import com.example.bookstore.repositories.BookRepo;
import com.example.bookstore.service.book.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepo bookRepo;

    private final BookServiceImpl bookServiceImpl;
    public BookController(BookServiceImpl bookService) {
        this.bookServiceImpl = bookService;
    }

    // Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    //Get a single book
    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long bookId) {
        BookResponse response = bookServiceImpl.getBookById(bookId);
        return ResponseEntity.ok(response);
    }

    // Add a new book
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepo.save(book);
    }

    //Update existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook (@PathVariable Long id, @RequestBody Book bookDetails){
        Book updateBook = bookServiceImpl.updateBook(id, bookDetails);
        return new ResponseEntity<>(updateBook, HttpStatus.CREATED);
    }


}
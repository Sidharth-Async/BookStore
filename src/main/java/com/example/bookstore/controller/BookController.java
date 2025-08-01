package com.example.bookstore.controller;

import com.example.bookstore.entities.Book;
import com.example.bookstore.repositories.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepo bookRepo;

    // Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    // Add a new book
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepo.save(book);
    }
}
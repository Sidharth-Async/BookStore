package com.example.bookstore.service.book;

import com.example.bookstore.dto.book.BookRequest;
import com.example.bookstore.dto.book.BookResponse;

public interface BookService {

    BookResponse createBook(BookRequest request);
}

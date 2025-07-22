package com.example.bookstore.service.book;

import com.example.bookstore.dto.book.BookRequest;
import com.example.bookstore.dto.book.BookResponse;
import com.example.bookstore.repositories.AuthorRepo;
import com.example.bookstore.repositories.BookRepo;
import com.example.bookstore.repositories.CategoryRepo;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl {

    private final BookRepo bookRepo;
    private final CategoryRepo categoryRepo;
    private final AuthorRepo authorRepo;

    public BookServiceImpl(BookRepo bookRepo,
                           CategoryRepo categoryRepo,
                           AuthorRepo authorRepo) {
        this.bookRepo = bookRepo;
        this.categoryRepo = categoryRepo;
        this.authorRepo = authorRepo;
    }


    public BookResponse createBook(BookRequest request){
        return null;
    }

}

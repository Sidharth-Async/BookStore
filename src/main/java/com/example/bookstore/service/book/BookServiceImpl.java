package com.example.bookstore.service.book;

import com.example.bookstore.dto.book.BookRequest;
import com.example.bookstore.dto.book.BookResponse;
import com.example.bookstore.entities.Author;
import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Category;
import com.example.bookstore.exceptions.BookNotFoundException;
import com.example.bookstore.repositories.AuthorRepo;
import com.example.bookstore.repositories.BookRepo;
import com.example.bookstore.repositories.CategoryRepo;
import com.example.bookstore.service.book.BookService;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {

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
    @Override
    public BookResponse createBook(BookRequest request) {

        //basic validation
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()){
            throw new IllegalArgumentException("Book title cannot be empty");
        }
        if (request.getPrice() <= 0 ) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        if (request.getPrice() < 0 ) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        //fetch related category
        var categories = categoryRepo.findById(request.getCategoryIds());
        if (categories.isEmpty()) {
            throw new RuntimeException("No valid categories found for the given IDs");
        }

        //fetch related authors
        var authors = authorRepo.findAllById(request.getAuthorIds());
        if (authors.isEmpty()) {
            throw  new RuntimeException("No valid authors found for the given IDs");
        }

        //create a new book entity
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setPrice(request.getPrice());
        book.setStock(request.getStock());
        book.setCategory();
        book.setAuthors(new HashSet<>(authors));

        //save to database
        Book savedBook = bookRepo.save(book);

        //convert saved book into entity  -> BookResponse DTO
        return mapToResponse(savedBook);
    }

    private BookResponse mapToResponse(Book book){
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setPrice(book.getPrice());
        response.setStock(book.getStock());

        // extract category names
        List<String> categoryNames = book.getCategory()
                .stream()
                .map(Category::getName)
                .toList();

        // extract author names
        List<String> authorNames = (List<String>) book.getAuthors()
                .stream();

        response.setCategories(categoryNames);
        response.setAuthors(authorNames);

        return response;
    }

    public BookResponse getBookById(Long bookId){

        // fetch book from DB
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + bookId + " not found"));
        return mapToResponse(book);

    }
}

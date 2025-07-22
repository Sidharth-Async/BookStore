package com.example.bookstore.dto.book;

import java.util.List;

public class BookResponse {

    private Long id;
    private String title;
    private double price;
    private int stock;
    private List<String> categories;
    private List<String> authors;
}

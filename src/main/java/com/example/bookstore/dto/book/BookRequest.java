package com.example.bookstore.dto.book;

import java.util.Set;

public class BookRequest {


    private String title;
    private double price;
    private int stock;
    private Set<Long> categoryIds;
    private Set<Long> authorIds;
}

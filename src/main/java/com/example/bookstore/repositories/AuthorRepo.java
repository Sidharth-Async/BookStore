package com.example.bookstore.repositories;

import com.example.bookstore.entities.Author;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author , Long> {
}

package com.example.bookstore.repositories;

import com.example.bookstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User , Integer> {
}

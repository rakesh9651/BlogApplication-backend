package com.application.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.blog.entities.Category;

public interface CategoryRespo extends JpaRepository<Category, Integer>{

}

package com.app.onlinebookstore.repository;

import com.app.onlinebookstore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

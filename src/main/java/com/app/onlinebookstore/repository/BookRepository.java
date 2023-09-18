package com.app.onlinebookstore.repository;

import com.app.onlinebookstore.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("FROM Book b LEFT JOIN FETCH b.categories c where c.id = :categoryId")
    List<Book> findAllByCategoryId(Long categoryId);
}

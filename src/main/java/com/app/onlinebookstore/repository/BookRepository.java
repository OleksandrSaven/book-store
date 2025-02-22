package com.app.onlinebookstore.repository;

import com.app.onlinebookstore.model.Book;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    @Query("FROM Book b LEFT JOIN FETCH b.categories c where c.id = :categoryId")
    List<Book> findAllByCategoryId(Long categoryId);

    @EntityGraph(attributePaths = {"categories"})
    Page<Book> findAll(Specification<Book> spec, Pageable pageable);

    List<Book> findAllByIdIn(List<Long> ids);
}

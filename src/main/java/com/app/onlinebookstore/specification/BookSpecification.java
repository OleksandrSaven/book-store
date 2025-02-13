package com.app.onlinebookstore.specification;

import com.app.onlinebookstore.model.Book;
import com.app.onlinebookstore.model.Category;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    public Specification<Book> searchBook(String author, String title, List<String> categories) {
        return ((root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (author != null && !author.isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(root.get("author"), "%" + author + "%"));
            }

            if (title != null && !title.isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(root.get("title"), "%" + title + "%"));
            }

            query.distinct(true);

            if (categories != null && !categories.isEmpty()) {
                Join<Book, Category> categoryJoin = root.join("categories", JoinType.INNER);
                predicate = criteriaBuilder.and(predicate,
                        categoryJoin.get("name").in(categories));
            }
            return predicate;
        });
    }
}

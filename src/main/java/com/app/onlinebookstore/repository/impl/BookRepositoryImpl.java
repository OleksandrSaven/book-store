package com.app.onlinebookstore.repository.impl;

import com.app.onlinebookstore.model.Book;
import com.app.onlinebookstore.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class BookRepositoryImpl implements BookRepository {
    private final SessionFactory sessionFactory;

    @Override
    public Book save(Book book) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert book into DB: " + book);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Book> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Book> getAllBooksQuery = session.createQuery("FROM Book ", Book.class);
            return getAllBooksQuery.getResultList();
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Book.class, id));
        }
    }
}

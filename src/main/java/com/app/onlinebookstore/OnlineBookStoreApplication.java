package com.app.onlinebookstore;

import com.app.onlinebookstore.model.Book;
import com.app.onlinebookstore.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineBookStoreApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(OnlineBookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Book book = new Book();
                book.setAuthor("John Townsend");
                book.setTitle("Boundaries");
                book.setIsbn("sn2334");
                book.setPrice(new BigDecimal(25));
                book.setDescription("Of your life");
                book.setCoverImage("Image");
                bookService.save(book);
                System.out.println(bookService.findAll());
            }
        };
    }
}

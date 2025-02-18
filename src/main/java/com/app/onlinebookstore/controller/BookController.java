package com.app.onlinebookstore.controller;

import com.app.onlinebookstore.dto.BookDto;
import com.app.onlinebookstore.dto.CreateBookRequestDto;
import com.app.onlinebookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book management", description = "Endpoints for managing books")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;

    @Operation(summary = "Get all books", description = "Get a list of all available books")
    @GetMapping
    public Page<BookDto> findAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @Operation(summary = "Create a new book", description = " Return created book")
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto bookDto) {
        return bookService.save(bookDto);
    }

    @Operation(summary = "Get a book with id", description = " Get a book with id")
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @Operation(summary = "Update info about existing book", description = " Get updated book")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BookDto update(@PathVariable Long id, @Valid
            @RequestBody CreateBookRequestDto bookRequestDto) {
        return bookService.update(id, bookRequestDto);
    }

    @GetMapping("/search")
    public Page<BookDto> searchBooks(@RequestParam(required = false) String author,
                                     @RequestParam(required = false) String title,
                                     @RequestParam(required = false) List<String> categories,
                                     Pageable pageable) {
        return bookService.searchBooks(author, title, categories, pageable);
    }

    @GetMapping("/filtered")
    public List<BookDto> getBooksByIds(@RequestParam(required = true) List<Long> ids) {
        return bookService.findAllById(ids);
    }
}

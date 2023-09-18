package com.app.onlinebookstore.controller;

import com.app.onlinebookstore.dto.BookDtoWithoutCategoryIds;
import com.app.onlinebookstore.dto.CategoryDto;
import com.app.onlinebookstore.dto.CreateCategoryRequestDto;
import com.app.onlinebookstore.service.BookService;
import com.app.onlinebookstore.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Category management", description = "Endpoints for managing categories")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @Operation(summary = "Create a new categories",
            description = " Return created category")
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CategoryDto createCategory(@RequestBody @Valid CreateCategoryRequestDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @Operation(summary = "Get all categories",
            description = "Get a list of all available categories")
    @GetMapping
    public List<CategoryDto> getAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @Operation(summary = "Get a category with id",
            description = " Get a category with id")
    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @Operation(summary = "Update info about existing category",
            description = " Get updated category")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CategoryDto updateCategory(@PathVariable Long id,
                                      @Valid @RequestBody CreateCategoryRequestDto requestDto) {
        return categoryService.update(id, requestDto);
    }

    @Operation(summary = "Delete the category with id",
            description = " Delete the category with id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @Operation(summary = "Get books by provided category",
            description = "List of books provided category")
    @GetMapping("/{id}/books")
    public List<BookDtoWithoutCategoryIds> getBooksByCategory(@PathVariable Long id) {
        return bookService.findAllCategoryId(id);
    }
}

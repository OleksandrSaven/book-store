package com.app.onlinebookstore.mapper;

import com.app.onlinebookstore.config.MapperConfig;
import com.app.onlinebookstore.dto.BookDto;
import com.app.onlinebookstore.dto.BookDtoWithoutCategoryIds;
import com.app.onlinebookstore.dto.CreateBookRequestDto;
import com.app.onlinebookstore.model.Book;
import com.app.onlinebookstore.model.Category;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDtoWithoutCategoryIds toDtoWithoutCategorise(Book book);

    BookDto toDto(Book book);

    @AfterMapping default void setCategoriesIds(
            @MappingTarget BookDto bookDto, Book book) {
        if (book.getCategories() != null) {
            bookDto.setCategoriesId(book.getCategories().stream()
                    .map(Category::getId)
                    .collect(Collectors.toSet()));
        }
    }

    Book toModel(CreateBookRequestDto requestDto);

    @AfterMapping
    default void setCategories(@MappingTarget Book book, CreateBookRequestDto requestDto) {
        if (requestDto.getCategoriesId() != null) {
            book.setCategories(requestDto.getCategoriesId().stream()
                    .map(Category::new)
                    .collect(Collectors.toSet()));
        }
    }
}

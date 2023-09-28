package com.app.onlinebookstore.service;

import com.app.onlinebookstore.dto.BookDto;
import com.app.onlinebookstore.dto.BookDtoWithoutCategoryIds;
import com.app.onlinebookstore.dto.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    List<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    void deleteById(Long id);

    BookDto update(Long id, CreateBookRequestDto book);

    List<BookDtoWithoutCategoryIds> findAllCategoryId(Long id);
}

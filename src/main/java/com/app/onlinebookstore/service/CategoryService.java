package com.app.onlinebookstore.service;

import com.app.onlinebookstore.dto.CategoryDto;
import com.app.onlinebookstore.dto.CreateCategoryRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto getById(Long id);

    CategoryDto save(CreateCategoryRequestDto category);

    CategoryDto update(Long id, CreateCategoryRequestDto category);

    void deleteById(Long id);
}

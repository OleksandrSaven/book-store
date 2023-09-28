package com.app.onlinebookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCategoryRequestDto {
    @NotBlank
    @Size(min = 3, max = 30)
    private String name;
    @NotBlank
    @Size(min = 6, max = 255)
    private String description;
}

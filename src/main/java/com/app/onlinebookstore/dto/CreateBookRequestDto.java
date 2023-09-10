package com.app.onlinebookstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateBookRequestDto {
    @NotNull
    @NotBlank
    @Length(min = 1, max = 255)
    private String title;
    @NotNull
    @NotBlank
    @Length(min = 8, max = 255)
    private String author;
    @NotNull
    @NotBlank
    @Length(min = 10, max = 255)
    private String isbn;
    @NotNull
    @NotBlank
    @Min(0)
    private BigDecimal price;
    private String description;
    private String coverImage;
}

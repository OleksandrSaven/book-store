package com.app.onlinebookstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCartItemDto {
    @NotBlank
    @Min(1)
    private Integer quantity;
}

package com.app.onlinebookstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCartItemRequestDto {
    @NotNull
    private Long bookId;
    @NotNull
    @Min(1)
    private int quantity;
}

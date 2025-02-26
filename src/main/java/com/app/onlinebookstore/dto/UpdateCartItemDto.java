package com.app.onlinebookstore.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateCartItemDto {
    @Min(1)
    private Integer quantity;
}

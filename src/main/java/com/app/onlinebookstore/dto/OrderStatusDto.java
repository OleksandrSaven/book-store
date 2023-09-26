package com.app.onlinebookstore.dto;

import com.app.onlinebookstore.model.Status;
import lombok.Data;

@Data
public class OrderStatusDto {
    private Status status;
}

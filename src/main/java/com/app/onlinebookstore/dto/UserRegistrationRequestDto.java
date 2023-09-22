package com.app.onlinebookstore.dto;

import com.app.onlinebookstore.validation.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@FieldMatch(field = "password", fieldMatch = "repeatPassword",
        message = "The password fields must match")
public class UserRegistrationRequestDto {
    @NotBlank
    @Email
    @Size(min = 4, max = 50)
    private String email;
    @NotBlank
    @Size(min = 4, max = 50)
    private String password;
    @NotBlank
    @Size(min = 4, max = 50)
    private String repeatPassword;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String shippingAddress;
}

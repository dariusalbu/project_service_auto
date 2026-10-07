package com.autoservice.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDTO {
    @Email(message = "Email not registered in the database")
    private String email;
    @NotBlank(message = "A password is mandatory")
    private String password;
}

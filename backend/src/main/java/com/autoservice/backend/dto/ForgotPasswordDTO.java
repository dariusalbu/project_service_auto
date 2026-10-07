package com.autoservice.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordDTO {
    @NotBlank(message = "Your email is mandatory")
    @Email(message = "The mail format is wrong/invalid")
    private String Email;
}

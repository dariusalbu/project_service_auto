package com.autoservice.backend.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDTO {
    @Email(message = "Email format is invalid")
    private String email;

    @NotBlank(message = "A password is mandatory")
    @Size(min = 6, message = "The password need to use atleast 6 characters")
    private String password;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phoneNumber;
}

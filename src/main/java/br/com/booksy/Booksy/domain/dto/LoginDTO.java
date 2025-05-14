package br.com.booksy.Booksy.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDTO {
    @Email(message = "invalid e-mail")
    private String email;

    @NotBlank(message = "password cannot be empty")
    @NotNull(message = "password cannot be empty")
    private String password;
}

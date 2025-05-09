package br.com.booksy.Booksy.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class UserRequestDTO {
    @NotBlank(message = "name cannot be empty")
    @NotNull(message = "name canot be empty")
    private String name;

    @Email(message = "invalid e-mail")
    private String email;

    @NotBlank(message = "password cannot be empty")
    @NotNull(message = "password cannot be empty")
    private String password;

    @NotNull(message = "isAdmin cannot be empty")
    private Boolean isAdmin;
}

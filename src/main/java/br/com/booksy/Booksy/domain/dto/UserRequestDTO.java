package br.com.booksy.Booksy.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class UserRequestDTO {
    private UUID id;
    @NotNull
    @NotBlank
    private String name;
    @Email(message = "Email inválido")
    private String email;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    private Boolean isAdmin;
}

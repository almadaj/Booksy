package br.com.booksy.Booksy.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRequestDTO {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Boolean isAdmin;
}

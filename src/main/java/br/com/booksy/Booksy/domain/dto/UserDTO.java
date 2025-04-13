package br.com.booksy.Booksy.domain.dto;

import lombok.*;

import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Boolean isAdmin;
}

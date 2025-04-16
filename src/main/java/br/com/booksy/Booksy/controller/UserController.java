package br.com.booksy.Booksy.controller;

import br.com.booksy.Booksy.domain.dto.UserRequestDTO;
import br.com.booksy.Booksy.domain.dto.UserResponseDTO;
import br.com.booksy.Booksy.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserResponseDTO findUserById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @PostMapping
    public UserResponseDTO save(@RequestBody @Valid UserRequestDTO user) {
        return userService.save(user);
    }

    @PutMapping
    public UserResponseDTO update(@RequestBody @Valid UserRequestDTO user) {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        userService.deleteById(id);
    }

}


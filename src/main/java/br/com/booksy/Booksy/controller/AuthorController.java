package br.com.booksy.Booksy.controller;

import br.com.booksy.Booksy.domain.dto.AuthorDTO;
import br.com.booksy.Booksy.domain.model.Author;
import br.com.booksy.Booksy.service.AuthorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Author")
@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> findAll() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(authorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Author> save(@RequestBody @Valid AuthorDTO author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.save(author));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable UUID id, @RequestBody @Valid AuthorDTO author) {
        return ResponseEntity.ok(authorService.update(id, author));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

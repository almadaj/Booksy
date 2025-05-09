package br.com.booksy.Booksy.controller;

import br.com.booksy.Booksy.domain.dto.CategoryDTO;
import br.com.booksy.Booksy.domain.dto.CategoryResponseDTO;
import br.com.booksy.Booksy.domain.model.Category;
import br.com.booksy.Booksy.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Category")
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> save(@RequestBody @Valid CategoryDTO category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid CategoryDTO category) {
        return ResponseEntity.ok(categoryService.update(id, category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

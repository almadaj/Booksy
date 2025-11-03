package br.com.booksy.Booksy.controller;

import br.com.booksy.Booksy.domain.dto.CategoryDTO;
import br.com.booksy.Booksy.domain.dto.CategoryResponseDTO;
import br.com.booksy.Booksy.domain.model.Category;
import br.com.booksy.Booksy.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria(s) encontrada(s) ou criado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content()),
        @ApiResponse(responseCode = "401", description = "Usuário não autorizado", content = @Content()),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content()),
        @ApiResponse(responseCode = "409", description = "Categoria com conflito", content = @Content()),
        @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content())
})
@Tag(name = "Category")
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;


    @Operation(summary = "Listar Categorias", description = "Retorna todas as Categories cadastradas")
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @Operation(summary = "Buscar uma Categoria por ID", description = "Retorna a Category com o ID correspondente")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @Operation(summary = "Criar uma Categoria", description = "Cria e retorna uma nova Category")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponseDTO> save(@RequestBody @Valid CategoryDTO category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
    }

    @Operation(summary = "Atualizar uma Categoria", description = "Atualiza os dados da Category fornecida")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid CategoryDTO category) {
        return ResponseEntity.ok(categoryService.update(id, category));
    }

    @ApiResponse(responseCode = "200", description = "Deletado com sucesso")
    @Operation(summary = "Excluir uma Categoria", description = "Remove a Category com o ID fornecido")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

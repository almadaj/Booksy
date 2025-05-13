package br.com.booksy.Booksy.controller;

import br.com.booksy.Booksy.domain.dto.AuthorDTO;
import br.com.booksy.Booksy.domain.dto.AuthorResponseDTO;
import br.com.booksy.Booksy.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Autor(es) encontrado(s) ou criado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content()),
        @ApiResponse(responseCode = "401", description = "Autor não autorizado", content = @Content()),
        @ApiResponse(responseCode = "404", description = "Autor não encontrado", content = @Content()),
        @ApiResponse(responseCode = "409", description = "Autor com conflito", content = @Content()),
        @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content())
})
@Tag(name = "Author")
@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @Operation(summary = "Buscar todos os Autores", description = "Retorna uma lista de Authors")
    public ResponseEntity<List<AuthorResponseDTO>> findAll() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @Operation(summary = "Buscar um Autor específico", description = "Retorna o Author buscado")
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(authorService.findById(id));
    }


    @Operation(summary = "Cria um Autor", description = "Retorna o Author criado")
    @PostMapping
    public ResponseEntity<AuthorResponseDTO> save(@RequestBody @Valid AuthorDTO author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.save(author));
    }

    @Operation(summary = "Edita um Autor específico", description = "Retorna o update feito em Author")
    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid AuthorDTO author) {
        return ResponseEntity.ok(authorService.update(id, author));
    }

    @ApiResponse(responseCode = "200", description = "Deletado com sucesso")
    @Operation(summary = "Edita um Autor específico", description = "Retorna o update feito em Author")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

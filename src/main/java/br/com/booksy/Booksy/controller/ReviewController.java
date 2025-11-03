package br.com.booksy.Booksy.controller;

import br.com.booksy.Booksy.domain.dto.ReviewRequestDTO;
import br.com.booksy.Booksy.domain.dto.ReviewResponseDTO;
import br.com.booksy.Booksy.domain.model.Review;
import br.com.booksy.Booksy.service.ReviewService;
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
        @ApiResponse(responseCode = "200", description = "Avaliação(ões) encontrada(s) ou criado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content()),
        @ApiResponse(responseCode = "401", description = "Usuário não autorizado", content = @Content()),
        @ApiResponse(responseCode = "404", description = "Avaliação não encontrada", content = @Content()),
        @ApiResponse(responseCode = "409", description = "Avaliação com conflito", content = @Content()),
        @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content())
})
@Tag(name = "Review")
@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(summary = "Listar Avaliações", description = "Retorna todas as Reviews registradas")
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<ReviewResponseDTO>> findAll() {
        return ResponseEntity.ok(reviewService.findAll());
    }

    @Operation(summary = "Buscar Avaliação por ID", description = "Retorna os dados da Review com o ID correspondente")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReviewResponseDTO> findById(@PathVariable UUID id){
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @Operation(summary = "Criar uma Avaliação", description = "Cria uma nova Review para um Livro")
    @PostMapping()
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReviewResponseDTO> save(@RequestBody @Valid ReviewRequestDTO reviewRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.save(reviewRequestDTO));
    }

    @Operation(summary = "Atualizar Avaliação", description = "Atualiza uma Review existente")
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReviewResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid ReviewRequestDTO reviewRequestDTO){
        return ResponseEntity.ok(reviewService.update(id, reviewRequestDTO));
    }

    @ApiResponse(responseCode = "200", description = "Deletado com sucesso")
    @Operation(summary = "Excluir Avaliação", description = "Remove uma Review")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

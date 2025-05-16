package br.com.booksy.Booksy.controller;

import br.com.booksy.Booksy.domain.dto.ReadingRequestDTO;
import br.com.booksy.Booksy.domain.dto.ReadingResponseDTO;
import br.com.booksy.Booksy.domain.model.User;
import br.com.booksy.Booksy.service.ReadingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import br.com.booksy.Booksy.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Leitura(s) encontrada(s) ou criado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content()),
        @ApiResponse(responseCode = "401", description = "Usuário não autorizado", content = @Content()),
        @ApiResponse(responseCode = "404", description = "Leitura não encontrada", content = @Content()),
        @ApiResponse(responseCode = "409", description = "Leitura com conflito", content = @Content()),
        @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content())
})
@Tag(name = "Reading")
@RestController
@RequestMapping("/api/v1/readings")
@RequiredArgsConstructor
public class ReadingController {
    private final ReadingService readingService;
    private final UserService userService;

    @Operation(summary = "Buscar Leitura por ID", description = "Retorna os dados de uma Reading específica")
    @GetMapping("/{id}")
    public ResponseEntity<ReadingResponseDTO> findReadingById(@PathVariable UUID id){
        return ResponseEntity.ok(readingService.findById(id));
    }

    @Operation(summary = "Iniciar uma Leitura", description = "Cria um registro de Reading para um Livro")
    @PostMapping()
    public ResponseEntity<ReadingResponseDTO> save(@RequestBody @Valid ReadingRequestDTO readingRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(readingService.save(readingRequestDTO));
    }

    @Operation(summary = "Lista as Leituras", description = "Retorna uma lista de Readings de um User")
    @GetMapping()
    public ResponseEntity<List<ReadingResponseDTO>> findByUserId(@AuthenticationPrincipal Jwt principal){
        String userEmail = principal.getSubject();
        User user = userService.findByEmail(userEmail);
        UUID userId = user.getId();
        List<ReadingResponseDTO> readings = readingService.findByUserId(userId);
        return ResponseEntity.ok(readings);
    }

    @Operation(summary = "Atualizar Leitura", description = "Atualiza o status ou progresso da Reading")
    @PutMapping("/{id}")
    public ResponseEntity<ReadingResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid ReadingRequestDTO readingRequestDTO){
        return ResponseEntity.ok(readingService.update(id, readingRequestDTO));
    }

    @Operation(summary = "Deleta uma Leitura", description = "Deleta um Reading")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        readingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

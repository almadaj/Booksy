package br.com.booksy.Booksy.controller;

import br.com.booksy.Booksy.domain.dto.ReadingRequestDTO;
import br.com.booksy.Booksy.domain.dto.ReadingResponseDTO;
import br.com.booksy.Booksy.service.ReadingService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<ReadingResponseDTO> findReadingById(@PathVariable UUID id){
        return ResponseEntity.ok(readingService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<ReadingResponseDTO> save(@RequestBody @Valid ReadingRequestDTO readingRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(readingService.save(readingRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReadingResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid ReadingRequestDTO readingRequestDTO){
        return ResponseEntity.ok(readingService.update(id, readingRequestDTO));
    }

    @ApiResponse(responseCode = "200", description = "Deletado com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        readingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

package br.com.booksy.Booksy.controller;

import br.com.booksy.Booksy.domain.dto.BookDTO;
import br.com.booksy.Booksy.domain.dto.BookResponseDTO;
import br.com.booksy.Booksy.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Livro(s) encontrado(s) ou criado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content()),
        @ApiResponse(responseCode = "401", description = "Usuário não autorizado", content = @Content()),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado", content = @Content()),
        @ApiResponse(responseCode = "409", description = "Livro com conflito", content = @Content()),
        @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content())
})
@Tag(name = "Book")
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @Operation(summary = "Buscar um Livros por ID", description = "Retorna Book de ID correspondente")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> findBookById(@PathVariable UUID id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @Operation(summary = "Buscar todos os Livros", description = "Retorna uma lista de Books")
    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @Operation(summary = "Criar um Livro", description = "Cria e retorna um novo Book")
    @PostMapping
    public ResponseEntity<BookResponseDTO> save(@RequestBody @Valid BookDTO bookDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookDTO));
    }

    @Operation(summary = "Atualizar um Livro", description = "Atualiza as informações de um Book existente")
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.update(id, bookDTO));
    }

    @ApiResponse(responseCode = "415", description = "Formato não suportado", content = @Content())
    @Operation(summary = "Upload de Livro", description = "Upload do livro referente")
    @PatchMapping(value = "/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadPdf(
            @PathVariable UUID id,
            @Parameter(
                    name = "file",
                    description = "PDF File",
                    required = true,
                    content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                            schema = @Schema(type = "string", format = "binary"))
            )
            @RequestPart("file") MultipartFile file) {

        return bookService.uploadPdf(id, file);
    }

    @Operation(summary = "Excluir um Livro", description = "Remove o Livro com o ID fornecido")
    @ApiResponse(responseCode = "200", description = "Deletado com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

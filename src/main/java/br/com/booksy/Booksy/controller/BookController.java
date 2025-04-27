package br.com.booksy.Booksy.controller;

import br.com.booksy.Booksy.domain.dto.BookDTO;
import br.com.booksy.Booksy.domain.dto.BookRequestDTO;
import br.com.booksy.Booksy.service.BookService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public BookDTO findBookById(@PathVariable UUID id) {
        return bookService.findById(id);
    }

    @GetMapping
    public List<BookDTO> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        return bookService.findAll(page, size);
    }

    @PostMapping
    public BookDTO save(@RequestBody @Valid BookRequestDTO bookDTO) {
        return bookService.save(bookDTO);
    }

    @PutMapping(value = "/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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

    @PutMapping
    public BookDTO update(@RequestBody @Valid BookDTO bookDTO) {
        return bookService.update(bookDTO);
    }

    @DeleteMapping("/{id}")
    public void deletById(@PathVariable UUID id) {
        bookService.deleteById(id);
    }

}

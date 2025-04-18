package br.com.booksy.Booksy.controller;

import br.com.booksy.Booksy.domain.dto.BookDTO;
import br.com.booksy.Booksy.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public BookDTO findBookById(UUID id) {
        return bookService.findById(id);
    }

    @GetMapping
    public List<BookDTO> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        return bookService.findAll(page, size);
    }

    @PostMapping
    public BookDTO save(@RequestBody @Valid BookDTO bookDTO) {
        return bookService.save(bookDTO);
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

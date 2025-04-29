package br.com.booksy.Booksy.controller;

import br.com.booksy.Booksy.domain.dto.ReadingDTO;
import br.com.booksy.Booksy.service.ReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/readings")
@RequiredArgsConstructor
public class ReadingController {
    private final ReadingService readingService;

    @GetMapping("/{id}")
    public ReadingDTO findReadingById(UUID id){
        return readingService.findById(id);
    }

    @PostMapping()
    public ReadingDTO save(@RequestBody ReadingDTO readingDTO){
        return readingService.save(readingDTO);
    }

    @PutMapping()
    public ReadingDTO update(@RequestBody ReadingDTO readingDTO){
        return readingService.update(readingDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id){
        readingService.deleteById(id);
    }
}

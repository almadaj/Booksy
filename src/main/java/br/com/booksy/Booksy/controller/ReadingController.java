package br.com.booksy.Booksy.controller;

import br.com.booksy.Booksy.domain.dto.ReadingRequestDTO;
import br.com.booksy.Booksy.service.ReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
//@RequestMapping("/api/v1/readings")
@RequestMapping("/api/v1/users/{userId}/readings")
@RequiredArgsConstructor
public class ReadingController {
    private final ReadingService readingService;

    @GetMapping("/{id}")
    public ReadingRequestDTO findReadingById(@PathVariable UUID id){
        return readingService.findById(id);
    }

    @PostMapping()
    public ReadingRequestDTO save(@RequestBody ReadingRequestDTO readingRequestDTO){
        return readingService.save(readingRequestDTO);
    }

    @PutMapping("/{id}")
    public ReadingRequestDTO update(@PathVariable UUID id, @RequestBody ReadingRequestDTO readingRequestDTO){
//        readingRequestDTO.setUserId(id);
        return readingService.update(readingRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id){
        readingService.deleteById(id);
    }
}

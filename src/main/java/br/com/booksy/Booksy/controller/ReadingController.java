package br.com.booksy.Booksy.controller;

import br.com.booksy.Booksy.domain.dto.ReadingRequestDTO;
import br.com.booksy.Booksy.domain.dto.ReadingResponseDTO;
import br.com.booksy.Booksy.domain.model.User;
import br.com.booksy.Booksy.service.ReadingService;
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

@Tag(name = "Reading")
@RestController
@RequestMapping("/api/v1/readings")
@RequiredArgsConstructor
public class ReadingController {
    private final ReadingService readingService;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ReadingResponseDTO> findReadingById(@PathVariable UUID id){
        return ResponseEntity.ok(readingService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<ReadingResponseDTO> save(@RequestBody @Valid ReadingRequestDTO readingRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(readingService.save(readingRequestDTO));
    }

    @GetMapping()
    public ResponseEntity<List<ReadingResponseDTO>> findByUserId(@AuthenticationPrincipal Jwt principal){
        String userEmail = principal.getSubject();
        User user = userService.findByEmail(userEmail);
        UUID userId = user.getId();
        List<ReadingResponseDTO> readings = readingService.findByUserId(userId);
        return ResponseEntity.ok(readings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReadingResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid ReadingRequestDTO readingRequestDTO){
        return ResponseEntity.ok(readingService.update(id, readingRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        readingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

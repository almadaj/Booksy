package br.com.booksy.Booksy.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ReviewResponseDTO {
    private UUID id;
    private int rating;
    private String title;
    private String textPost;
    private UserResponseLowDTO user;
    private BookResponseLowDTO book;
}
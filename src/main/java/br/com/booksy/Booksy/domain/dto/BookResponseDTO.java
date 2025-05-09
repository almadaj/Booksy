package br.com.booksy.Booksy.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BookResponseDTO {
    private UUID id;
    private String title;
    private String isbn;
    private String uploadId;
    private Integer pagesNumber;
    private Integer releaseDate;
/*    private AuthorResponseDTO author;
    private List<CategoryResponseDTO> categories;*/
}

package br.com.booksy.Booksy.service;

import br.com.booksy.Booksy.domain.dto.AuthorDTO;
import br.com.booksy.Booksy.domain.dto.AuthorResponseDTO;
import br.com.booksy.Booksy.domain.model.Author;
import br.com.booksy.Booksy.exception.CommonException;
import br.com.booksy.Booksy.domain.mapper.AuthorMapper;
import br.com.booksy.Booksy.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<AuthorResponseDTO> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorMapper.INSTANCE::toAuthorResponseDTO)
                .collect(Collectors.toList());
    }

    public AuthorResponseDTO findById(UUID id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new CommonException(HttpStatus.NOT_FOUND, "booksy.author.findById.notFound", "Author not found"));
        return AuthorMapper.INSTANCE.toAuthorResponseDTO(author);
    }

    public Author findAuthorById(UUID id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new CommonException(HttpStatus.NOT_FOUND, "booksy.author.findById.notFound", "Author not found"));
    }

    @Transactional
    public AuthorResponseDTO save(AuthorDTO authorDTO) {
        Author savedAuthor = authorRepository.save(AuthorMapper.INSTANCE.toAuthor(authorDTO));
        return AuthorMapper.INSTANCE.toAuthorResponseDTO(savedAuthor);
    }

    @Transactional
    public AuthorResponseDTO update(UUID id, AuthorDTO authorDTO) {
        Author savedAuthor = findAuthorById(id);
        Author author = AuthorMapper.INSTANCE.toAuthor(authorDTO);
        author.setId(savedAuthor.getId());
        author.setCreatedAt(savedAuthor.getCreatedAt());
        author.setUpdatedAt(savedAuthor.getUpdatedAt());
        Author updatedAuthor = authorRepository.save(author);
        return AuthorMapper.INSTANCE.toAuthorResponseDTO(updatedAuthor);
    }

    @Transactional
    public void delete(UUID id) {
        authorRepository.delete(findAuthorById(id));
    }
}

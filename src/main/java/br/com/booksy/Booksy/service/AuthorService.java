package br.com.booksy.Booksy.service;

import br.com.booksy.Booksy.domain.dto.AuthorDTO;
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

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(UUID id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new CommonException(HttpStatus.NOT_FOUND, "booksy.author.findById.notFound", "Author not found"));
    }

    @Transactional
    public Author save(AuthorDTO authorDTO) {
        return authorRepository.save(AuthorMapper.INSTANCE.toAuthor(authorDTO));
    }

    @Transactional
    public Author update(UUID id, AuthorDTO authorDTO) {
        Author savedAuthor = findById(id);
        Author author = AuthorMapper.INSTANCE.toAuthor(authorDTO);
        author.setId(savedAuthor.getId());
        return authorRepository.save(author);
    }

    @Transactional
    public void delete(UUID id) {
        authorRepository.delete(findById(id));
    }
}

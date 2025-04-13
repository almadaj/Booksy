package br.com.booksy.Booksy.service;

import br.com.booksy.Booksy.domain.dto.BookDTO;
import br.com.booksy.Booksy.domain.mapper.BookMapper;
import br.com.booksy.Booksy.exception.CommonException;
import br.com.booksy.Booksy.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private BookMapper bookMapper;

    public BookDTO findById(UUID id) {
        return bookRepository.findById(id)
                .map(book -> bookMapper.bookToBookDTO(book))
                .orElseThrow(
                () -> new CommonException(HttpStatus.NOT_FOUND,  "booksy.book.findById.notFound", "Book not found")
        );
    }

    public BookDTO save(BookDTO bookDTO) {
        try {
            var newUser = bookRepository.save(bookMapper.bookDTOtoBook(bookDTO));
            return bookMapper.bookToBookDTO(newUser);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.book.save.badRequest", "Error while saving book");
        }
    }

    public BookDTO update(BookDTO bookDTO) {
        try {
            var updatedBook = bookRepository.save(bookMapper.bookDTOtoBook(bookDTO));
            return bookMapper.bookToBookDTO(updatedBook);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.book.save.badRequest", "Error while updating book");
        }
    }

    public void deleteById(UUID id) {
        bookRepository.deleteById(id);
    }
}

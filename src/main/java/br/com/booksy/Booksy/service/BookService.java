package br.com.booksy.Booksy.service;

import br.com.booksy.Booksy.domain.dto.BookDTO;
import br.com.booksy.Booksy.domain.dto.BookResponseDTO;
import br.com.booksy.Booksy.domain.mapper.BookMapper;
import br.com.booksy.Booksy.domain.model.Book;
import br.com.booksy.Booksy.exception.CommonException;
import br.com.booksy.Booksy.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookResponseDTO findById(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new CommonException(HttpStatus.NOT_FOUND,  "booksy.book.findById.notFound", "Book not found"));
        return bookMapper.booktoBookResponseDTO(book);
    }

    public Book findBookById(UUID id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new CommonException(HttpStatus.NOT_FOUND,  "booksy.book.findById.notFound", "Book not found"));
    }

    public List<BookResponseDTO> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::booktoBookResponseDTO)
                .collect(Collectors.toList());
    }

    public BookResponseDTO save(BookDTO bookDTO) {
        Book savedBook = bookRepository.save(bookMapper.bookDTOtoBook(bookDTO));
        return bookMapper.booktoBookResponseDTO(savedBook);
    }

    public BookResponseDTO update(UUID id, BookDTO bookDTO) {
        try {
            Book savedBook = findBookById(id);
            Book book = bookMapper.bookDTOtoBook(bookDTO);
            book.setId(savedBook.getId());
            book.setCreatedAt(savedBook.getCreatedAt());
            book.setUpdatedAt(savedBook.getUpdatedAt());
            Book updatedBook = bookRepository.save(book);
            return bookMapper.booktoBookResponseDTO(updatedBook);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.book.save.badRequest", "Error while updating book");
        }
    }

    public void deleteById(UUID id) {
        bookRepository.delete(findBookById(id));
    }
}

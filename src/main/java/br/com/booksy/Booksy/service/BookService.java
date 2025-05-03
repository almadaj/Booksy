package br.com.booksy.Booksy.service;

import br.com.booksy.Booksy.domain.dto.BookDTO;
import br.com.booksy.Booksy.domain.dto.BookRequestDTO;
import br.com.booksy.Booksy.domain.dto.BookUpload;
import br.com.booksy.Booksy.domain.mapper.BookMapper;
import br.com.booksy.Booksy.domain.model.Book;
import br.com.booksy.Booksy.exception.CommonException;
import br.com.booksy.Booksy.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final GoogleDriveService googleDriveService;

    public BookDTO findById(UUID id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDTO)
                .orElseThrow(
                () -> new CommonException(HttpStatus.NOT_FOUND,  "booksy.book.findById.notFound", "Book not found")
        );
    }

    public List<BookDTO> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());
        return this.bookRepository.findAll(pageable).stream().map(bookMapper::toDTO).collect(Collectors.toList());
    }

    public BookDTO save(BookRequestDTO bookDTO) {
        try {
            var bookEntity = bookMapper.toBook(bookDTO);
            var savedBook = bookRepository.save(bookEntity);

            return bookMapper.toDTO(savedBook);
        }
        catch (DataIntegrityViolationException e) {
            throw new CommonException(HttpStatus.CONFLICT, "booksy.book.save.conflict", "A conflict occurred while saving the book. It may already exist or violate database constraints.");
        }
        catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.book.save.badRequest", "Error while saving book");
        }
    }

    public String uploadPdf(UUID id, MultipartFile file) {
        validateFile(file);

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new CommonException(
                        HttpStatus.NOT_FOUND,
                        "booksy.book.uploadPdf.notFound",
                        "Book not found"
                ));

        BookUpload bookUpload = googleDriveService.uploadFile(book.getId().toString(), file);

        book.setUploadId(bookUpload.id());
        book.setViewLink(bookUpload.viewLink());

        bookRepository.save(book);

        return bookUpload.viewLink();
    }


    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.book.save.badRequest", "File must be provided.");
        }

        if (!MediaType.APPLICATION_PDF_VALUE.equalsIgnoreCase(file.getContentType())) {
            throw new CommonException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "booksy.book.save.badRequest", "Only PDF files are allowed.");
        }
    }

    public BookDTO update(BookDTO bookDTO) {
        try {
            bookDTO.setId(null);
            var updatedBook = bookRepository.save(bookMapper.toBook(bookDTO));
            return bookMapper.toDTO(updatedBook);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.book.save.badRequest", "Error while updating book");
        }
    }

    public void deleteById(UUID id) {
        bookRepository.findById(id).ifPresent(book -> {
            googleDriveService.deleteFile(book.getUploadId());
            bookRepository.deleteById(id);
        });
    }
}

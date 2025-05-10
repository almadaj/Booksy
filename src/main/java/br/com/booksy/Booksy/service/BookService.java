package br.com.booksy.Booksy.service;

import br.com.booksy.Booksy.domain.dto.BookDTO;
import br.com.booksy.Booksy.domain.dto.BookResponseDTO;
import br.com.booksy.Booksy.domain.dto.BookUpload;
import br.com.booksy.Booksy.domain.mapper.BookMapper;
import br.com.booksy.Booksy.domain.model.Book;
import br.com.booksy.Booksy.exception.CommonException;
import br.com.booksy.Booksy.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final GoogleDriveService googleDriveService;

    public BookResponseDTO findById(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new CommonException(HttpStatus.NOT_FOUND,  "booksy.book.findById.notFound", "Book not found"));
        return bookMapper.bookToBookResponseDTO(book);
    }

    public Book findBookById(UUID id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new CommonException(HttpStatus.NOT_FOUND,  "booksy.book.findById.notFound", "Book not found"));
    }

    public List<BookResponseDTO> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::bookToBookResponseDTO)
                .toList();
    }

    public BookResponseDTO save(BookDTO bookDTO) {
        Book savedBook = bookRepository.save(bookMapper.bookDTOtoBook(bookDTO));
        return bookMapper.bookToBookResponseDTO(savedBook);
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

    public BookResponseDTO update(UUID id, BookDTO bookDTO) {
        try {
            Book savedBook = findBookById(id);
            Book book = bookMapper.bookDTOtoBook(bookDTO);
            book.setId(savedBook.getId());
            book.setCreatedAt(savedBook.getCreatedAt());
            book.setUpdatedAt(savedBook.getUpdatedAt());
            Book updatedBook = bookRepository.save(book);
            return bookMapper.bookToBookResponseDTO(updatedBook);
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

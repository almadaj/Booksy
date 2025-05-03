package br.com.booksy.Booksy.service;

import br.com.booksy.Booksy.domain.dto.BookDTO;
import br.com.booksy.Booksy.domain.dto.BookRequestDTO;
import br.com.booksy.Booksy.domain.dto.BookUpload;
import br.com.booksy.Booksy.domain.mapper.BookMapper;
import br.com.booksy.Booksy.exception.CommonException;
import br.com.booksy.Booksy.repository.BookRepository;
import lombok.RequiredArgsConstructor;
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

        var bookEntity = bookMapper.toBook(bookDTO);
        var savedBook = bookRepository.save(bookEntity);

        return bookMapper.toDTO(savedBook);
    }

    public String uploadPdf(UUID id, MultipartFile file) {
        validateFile(file);
        var book = bookRepository.findById(id);

        BookUpload bookUpload = book.map(value -> googleDriveService.uploadFile(value.getId().toString(), file))
                .orElseThrow(
                        () -> new CommonException(HttpStatus.NOT_FOUND,  "booksy.book.uploadPdf.notFound", "Book not found"));

        book.get().setUploadId(bookUpload.id());
        book.get().setViewLink(bookUpload.viewLink());

        bookRepository.save(book.get());

        return bookUpload.viewLink();
    }

    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.book.save.badRequest", "File must be provided.");
        }

        if (!MediaType.APPLICATION_PDF_VALUE.equalsIgnoreCase(file.getContentType())) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.book.save.badRequest", "Only PDF files are allowed.");
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
        var book = bookRepository.findById(id);
        book.ifPresent(value -> googleDriveService.deleteFile(value.getUploadId()));
        bookRepository.deleteById(id);
    }
}

package br.com.booksy.Booksy.domain.mapper;

import br.com.booksy.Booksy.domain.dto.ReadingRequestDTO;
import br.com.booksy.Booksy.domain.dto.ReadingResponseDTO;
import br.com.booksy.Booksy.domain.model.Book;
import br.com.booksy.Booksy.domain.model.Reading;
import br.com.booksy.Booksy.domain.model.User;
import br.com.booksy.Booksy.service.BookService;
import br.com.booksy.Booksy.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class ReadingMapper {
    @Autowired
    protected BookService bookService;

    @Autowired
    protected UserService userService;

    @Mapping(target = "book", source = "bookId", qualifiedByName = "mapBook")
    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUser")
    public abstract Reading readingRequestDTOtoReading(ReadingRequestDTO readingRequestDTO);

    public abstract ReadingResponseDTO readingToReadingResponseDTO(Reading Reading);

    @Named("mapBook")
    protected Book mapBook(UUID bookId) {
        return bookService.findBookById(bookId);
    }

    @Named("mapUser")
    protected User mapUser(UUID userId) {
        return userService.findUserById(userId);
    }
}

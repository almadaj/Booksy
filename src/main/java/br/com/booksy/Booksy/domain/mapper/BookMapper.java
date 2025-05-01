package br.com.booksy.Booksy.domain.mapper;

import br.com.booksy.Booksy.domain.dto.BookDTO;
import br.com.booksy.Booksy.domain.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book bookDTOtoBook(BookDTO bookDTO);
    BookDTO bookToBookDTO(Book book);
}

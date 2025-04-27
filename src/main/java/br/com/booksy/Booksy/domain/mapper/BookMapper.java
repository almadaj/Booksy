package br.com.booksy.Booksy.domain.mapper;

import br.com.booksy.Booksy.domain.dto.BookDTO;
import br.com.booksy.Booksy.domain.dto.BookRequestDTO;
import br.com.booksy.Booksy.domain.model.Book;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
    Book toBook(BookDTO bookDTO);
    Book toBook(BookRequestDTO bookRequestDTO);
    BookDTO toDTO(Book book);
}

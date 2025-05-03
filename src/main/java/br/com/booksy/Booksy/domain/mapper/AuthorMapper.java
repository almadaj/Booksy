package br.com.booksy.Booksy.domain.mapper;

import br.com.booksy.Booksy.domain.dto.AuthorDTO;
import br.com.booksy.Booksy.domain.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper()
public abstract class AuthorMapper {
    public static final AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    public abstract AuthorDTO toAuthorDTO(Author author);

    public abstract Author toAuthor(AuthorDTO authorDTO);
}

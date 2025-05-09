package br.com.booksy.Booksy.domain.mapper;

import br.com.booksy.Booksy.domain.dto.BookDTO;
import br.com.booksy.Booksy.domain.dto.BookResponseDTO;
import br.com.booksy.Booksy.domain.model.Author;
import br.com.booksy.Booksy.domain.model.Book;
import br.com.booksy.Booksy.domain.model.Category;
import br.com.booksy.Booksy.service.AuthorService;
import br.com.booksy.Booksy.service.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class BookMapper {
    @Autowired
    protected AuthorService authorService;

    @Autowired
    protected CategoryService categoryService;

    @Mapping(target = "author", source = "authorId", qualifiedByName = "mapAuthor")
    @Mapping(target = "categories", source = "categoryIds", qualifiedByName = "mapCategories")
    public abstract Book bookDTOtoBook(BookDTO bookDTO);

    public abstract BookResponseDTO booktoBookResponseDTO(Book book);

    @Named("mapAuthor")
    protected Author mapAuthor(UUID authorId) {
        return authorService.findAuthorById(authorId);
    }

    @Named("mapCategories")
    protected Set<Category> mapCategories(Set<UUID> ids) {
        if (ids == null) {
            return Collections.emptySet();
        }
        return categoryService.findAllByIds(ids);
    }
}

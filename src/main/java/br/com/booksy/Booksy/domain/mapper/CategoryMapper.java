package br.com.booksy.Booksy.domain.mapper;

import br.com.booksy.Booksy.domain.dto.CategoryDTO;
import br.com.booksy.Booksy.domain.dto.CategoryResponseDTO;
import br.com.booksy.Booksy.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
    public static final CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    public abstract CategoryResponseDTO toCategoryResponseDTO(Category category);

    public abstract Category toCategory(CategoryDTO categoryDTO);
}

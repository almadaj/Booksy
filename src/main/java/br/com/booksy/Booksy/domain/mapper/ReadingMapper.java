package br.com.booksy.Booksy.domain.mapper;

import br.com.booksy.Booksy.domain.dto.ReadingRequestDTO;
import br.com.booksy.Booksy.domain.model.Reading;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReadingMapper {
    Reading readingDTOtoReading(ReadingRequestDTO readingRequestDTO);
    ReadingRequestDTO readingToReadingDTO(Reading Reading);
}

package br.com.booksy.Booksy.domain.mapper;

import br.com.booksy.Booksy.domain.dto.ReadingDTO;
import br.com.booksy.Booksy.domain.model.Reading;
import org.mapstruct.Mapper;

@Mapper
public interface ReadingMapper {
    Reading readingDTOtoReading(ReadingDTO readingDTO);
    ReadingDTO readingToReadingDTO(Reading Reading);
}

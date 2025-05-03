package br.com.booksy.Booksy.service;

import br.com.booksy.Booksy.domain.dto.ReadingRequestDTO;
import br.com.booksy.Booksy.domain.mapper.ReadingMapper;
import br.com.booksy.Booksy.repository.ReadingRepository;
import br.com.booksy.Booksy.exception.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReadingService {
    private final ReadingRepository readingRepository;
    private ReadingMapper readingMapper;

    public ReadingRequestDTO findById(UUID id){
        return readingRepository.findById(id)
                .map(reading -> readingMapper.readingToReadingDTO(reading))
                .orElseThrow(
                        () -> new CommonException(HttpStatus.NOT_FOUND,  "booksy.book.findById.notFound", "Reading not found"));
    }

    public ReadingRequestDTO save(ReadingRequestDTO readingRequestDTO){
        try{
            var newReading = readingRepository.save(readingMapper.readingDTOtoReading(readingRequestDTO));
            return readingMapper.readingToReadingDTO(newReading);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.book.save.badRequest", "Error while saving reading");
        }
    }

    public ReadingRequestDTO update(ReadingRequestDTO readingRequestDTO) {
        try {
            var updatedReading = readingRepository.save(readingMapper.readingDTOtoReading(readingRequestDTO));
            return readingMapper.readingToReadingDTO(updatedReading);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.book.save.badRequest", "Error while updating reading");
        }
    }

    public void deleteById(UUID id) {
        readingRepository.deleteById(id);
    }
}

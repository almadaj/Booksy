package br.com.booksy.Booksy.service;

import br.com.booksy.Booksy.domain.dto.ReadingRequestDTO;
import br.com.booksy.Booksy.domain.dto.ReadingResponseDTO;
import br.com.booksy.Booksy.domain.mapper.ReadingMapper;
import br.com.booksy.Booksy.domain.model.Reading;
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
    private final ReadingMapper readingMapper;

    public ReadingResponseDTO findById(UUID id) {
        Reading reading = readingRepository.findById(id)
                .orElseThrow(() -> new CommonException(HttpStatus.NOT_FOUND, "booksy.book.findById.notFound", "Reading not found"));
        return readingMapper.readingToReadingResponseDTO(reading);
    }

    public Reading findReadingById(UUID id) {
        return readingRepository.findById(id)
                .orElseThrow(() -> new CommonException(HttpStatus.NOT_FOUND, "booksy.book.findById.notFound", "Reading not found"));
    }

    public ReadingResponseDTO save(ReadingRequestDTO readingRequestDTO) {
        Reading savedReading = readingRepository.save(readingMapper.readingRequestDTOtoReading(readingRequestDTO));
        return readingMapper.readingToReadingResponseDTO(savedReading);
    }

    public ReadingResponseDTO update(UUID id, ReadingRequestDTO readingRequestDTO) {
        try {
            Reading savedReading = findReadingById(id);
            Reading reading = readingMapper.readingRequestDTOtoReading(readingRequestDTO);
            reading.setId(savedReading.getId());
            reading.setCreatedAt(savedReading.getCreatedAt());
            reading.setUpdatedAt(savedReading.getUpdatedAt());
            Reading updatedReading = readingRepository.save(reading);
            return readingMapper.readingToReadingResponseDTO(readingRepository.save(updatedReading));
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.book.save.badRequest", "Error while updating reading");
        }
    }

    public void deleteById(UUID id) {
        readingRepository.delete(findReadingById(id));
    }
}

package br.com.booksy.Booksy.service;

import br.com.booksy.Booksy.domain.dto.UserRequestDTO;
import br.com.booksy.Booksy.domain.dto.UserResponseDTO;
import br.com.booksy.Booksy.domain.mapper.UserMapper;
import br.com.booksy.Booksy.exception.CommonException;
import br.com.booksy.Booksy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDTO findById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::userToUserResponseDTO)
                .orElseThrow(
                () -> new CommonException(HttpStatus.NOT_FOUND,  "booksy.user.findById.notFound", "User not found")
        );
    }

    public UserResponseDTO save(UserRequestDTO userDTO) {
        try {
            userDTO.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
            var newUser = userRepository.save(userMapper.userRequestDTOtoUser(userDTO));
            return userMapper.userToUserResponseDTO(newUser);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.user.save.badRequest", e.getMessage());
        }
    }

    public UserResponseDTO update(UserRequestDTO userDTO) {
        try {
            var updatedUser = userRepository.save(userMapper.userRequestDTOtoUser(userDTO));
            return userMapper.userToUserResponseDTO(updatedUser);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.user.save.badRequest", "Error while updating user");
        }
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}

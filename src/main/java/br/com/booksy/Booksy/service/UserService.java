package br.com.booksy.Booksy.service;

import br.com.booksy.Booksy.domain.dto.UserRequestDTO;
import br.com.booksy.Booksy.domain.dto.UserResponseDTO;
import br.com.booksy.Booksy.domain.mapper.UserMapper;
import br.com.booksy.Booksy.domain.model.User;
import br.com.booksy.Booksy.exception.CommonException;
import br.com.booksy.Booksy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public User findUserById (UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CommonException(HttpStatus.NOT_FOUND,  "booksy.user.findById.notFound", "User not found"));
    }

    public List<UserResponseDTO> findAll() {
        return this.userRepository.findAll().stream().map(userMapper::userToUserResponseDTO).collect(Collectors.toList());
    }

    public UserResponseDTO save(UserRequestDTO userRequestDTO) {
        try {
            userRequestDTO.setPassword(new BCryptPasswordEncoder().encode(userRequestDTO.getPassword()));
            User newUser = userMapper.userRequestDTOtoUser(userRequestDTO);
            newUser.setIsAdmin(false);
            User savedUser = userRepository.save(newUser);
            return userMapper.userToUserResponseDTO(savedUser);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.user.save.badRequest", "Error while saving user");
        }
    }

    public UserResponseDTO update(UUID id, UserRequestDTO userDTO) {
        try {
            User savedUser = findUserById(id);
            User user = userMapper.userRequestDTOtoUser(userDTO);
            user.setId(savedUser.getId());
            user.setCreatedAt(savedUser.getCreatedAt());
            user.setUpdatedAt(savedUser.getUpdatedAt());
            User updatedUser = userRepository.save(user);
            return userMapper.userToUserResponseDTO(updatedUser);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.user.save.badRequest", "Error while updating user");
        }
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}

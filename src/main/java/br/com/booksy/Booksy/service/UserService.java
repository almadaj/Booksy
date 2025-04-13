package br.com.booksy.Booksy.service;

import br.com.booksy.Booksy.domain.dto.UserDto;
import br.com.booksy.Booksy.domain.mapper.UserMapper;
import br.com.booksy.Booksy.domain.model.User;
import br.com.booksy.Booksy.exception.CommonException;
import br.com.booksy.Booksy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private UserMapper userMapper;

    public UserDto findById(UUID id) {
        return userRepository.findById(id)
                .map(user -> userMapper.userToUserDTO(user))
                .orElseThrow(
                () -> new CommonException(HttpStatus.NOT_FOUND,  "booksy.user.findById.notFound", "User not found")
        );
    }

    public User save(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.user.save.badRequest", "Error while saving user");
        }
    }

    public User update(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "booksy.user.save.badRequest", "Error while updating user");
        }
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}

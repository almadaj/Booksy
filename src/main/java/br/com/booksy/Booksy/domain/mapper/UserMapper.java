package br.com.booksy.Booksy.domain.mapper;

import br.com.booksy.Booksy.domain.dto.UserRequestDTO;
import br.com.booksy.Booksy.domain.dto.UserResponseDTO;
import br.com.booksy.Booksy.domain.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User userRequestDTOtoUser(UserRequestDTO userRequestDTO);
    UserResponseDTO userToUserResponseDTO(User user);
}

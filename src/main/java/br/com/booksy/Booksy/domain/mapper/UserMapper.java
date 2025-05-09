package br.com.booksy.Booksy.domain.mapper;

import br.com.booksy.Booksy.domain.dto.UserRequestDTO;
import br.com.booksy.Booksy.domain.dto.UserResponseDTO;
import br.com.booksy.Booksy.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public abstract User userRequestDTOtoUser(UserRequestDTO userRequestDTO);
    public abstract UserResponseDTO userToUserResponseDTO(User user);
}

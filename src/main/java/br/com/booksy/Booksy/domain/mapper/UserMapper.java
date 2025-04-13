package br.com.booksy.Booksy.domain.mapper;

import br.com.booksy.Booksy.domain.dto.UserDTO;
import br.com.booksy.Booksy.domain.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User userDTOtoUser(UserDTO userDto);
    UserDTO userToUserDTO(User user);
}

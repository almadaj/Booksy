package br.com.booksy.Booksy.domain.mapper;

import br.com.booksy.Booksy.domain.dto.UserDto;
import br.com.booksy.Booksy.domain.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User userDTOtoUser(UserDto userDto);
    UserDto userToUserDTO(User user);
}

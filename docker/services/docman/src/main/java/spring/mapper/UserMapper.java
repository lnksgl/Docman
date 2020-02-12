package spring.mapper;

import org.mapstruct.Mapper;
import spring.dto.UserDto;
import spring.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User user);

    User dtoToUser(UserDto userDto);
}

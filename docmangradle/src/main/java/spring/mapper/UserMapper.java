package spring.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;
import spring.dto.UserDto;
import spring.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User user);

    User dtoToUser(UserDto userDto);
}

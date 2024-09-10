package hr.tis.academy.mapper;

import hr.tis.academy.dto.UserDto;
import hr.tis.academy.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "attractionsUser", ignore = true)
    @Mapping(target = "userReviews", ignore = true)
    User toUserEntity(UserDto UserDto);

    UserDto toUserDto(User userEntity);

    List<UserDto> toUserDtoList(List<User> UserEntityList);
}

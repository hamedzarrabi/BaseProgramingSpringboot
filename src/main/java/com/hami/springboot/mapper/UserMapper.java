package com.hami.springboot.mapper;

import com.hami.springboot.dto.UserDto;
import com.hami.springboot.entiity.User;

public class UserMapper {

    // Convert User JPA entity to userDto
    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );

        return userDto;
    }

    // convert userDto into user JPA Entity
    public static User mapToUser(UserDto userDto) {
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getFirstName(),
                userDto.getEmail()
        );

        return user;
    }


}

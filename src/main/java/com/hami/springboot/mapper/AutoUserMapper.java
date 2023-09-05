package com.hami.springboot.mapper;

import com.hami.springboot.dto.UserDto;
import com.hami.springboot.entiity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface AutoUserMapper {
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserDto maptoUserDto(User user);
    User mapToUser(UserDto userDto);
}

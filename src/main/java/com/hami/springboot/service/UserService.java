package com.hami.springboot.service;

import com.hami.springboot.dto.UserDto;
import com.hami.springboot.entiity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto userDto);
    void deleteUser(Long userId);
}

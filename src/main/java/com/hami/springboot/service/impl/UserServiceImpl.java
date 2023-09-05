package com.hami.springboot.service.impl;

import com.hami.springboot.dto.UserDto;
import com.hami.springboot.entiity.User;
import com.hami.springboot.exception.EmailAlreadyExistsException;
import com.hami.springboot.exception.ResourceNotFoundException;
import com.hami.springboot.mapper.AutoUserMapper;
import com.hami.springboot.mapper.UserMapper;
import com.hami.springboot.repository.UserRepository;
import com.hami.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        // convert userDto into user JPA Entity
        // User user = UserMapper.mapToUser(userDto);

        // User user = modelMapper.map(userDto, User.class);

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        // Convert User JPA entity to userDto
        // UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

         // UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
         UserDto savedUserDto = AutoUserMapper.MAPPER.maptoUserDto(user);

        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        // User user = optionalUser.get();
        // return UserMapper.mapToUserDto(user);

        // return modelMapper.map(user, UserDto.class);

        return AutoUserMapper.MAPPER.maptoUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users =  userRepository.findAll();
//        return user.stream().map(UserMapper::mapToUserDto)
//                .collect(Collectors.toList());

        // return users.stream().map((user) -> modelMapper.map(user, UserDto.class))
        //        .collect(Collectors.toList());

        return users.stream().map((user) -> AutoUserMapper.MAPPER.maptoUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        User existingUser = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userDto.getId())
        );

        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());

        User updatedUser = userRepository.save(existingUser);

//        return UserMapper.mapToUserDto(updatedUser);

        // return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.maptoUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }
}

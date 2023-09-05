package com.hami.springboot.controller;

import com.hami.springboot.dto.UserDto;
import com.hami.springboot.entiity.User;
import com.hami.springboot.exception.ErrorDetails;
import com.hami.springboot.exception.ResourceNotFoundException;
import com.hami.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "create user, update, get user, get all users, delete user"
)
public class UserController {

    private UserService userService;

    // build create User REST API
    @Operation(
            summary = "create user REST API",
            description = "create user REST APIs is used to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user) {
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // build find User by id REST API
    @Operation(
            summary = "get user by id REST API",
            description = "get user REST APIs is used to get a single user from the  database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    // Build get all Users REST API b
    @Operation(
            summary = "get all users REST API",
            description = "get all users REST APIs is used to get a all user from the  database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("")
    public ResponseEntity<List<UserDto>> findAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Build update user REST API
    @Operation(
            summary = "update user REST API",
            description = "update user REST APIs is used to update a particular user from the  database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("id") Long id,
            @RequestBody @Valid UserDto user
    ) {
        user.setId(id);
        UserDto updateUser = userService.updateUser(user);

        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    // Build delete User REST API
    @Operation(
            summary = "delete user by id REST API",
            description = "delete user REST APIs is used to get a particular user from the  database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
//            ResourceNotFoundException exception,
//            WebRequest webRequest
//    ) {
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false), // if you don't need user information
//                "USER_NOT_FOUND"
//        );
//
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
}

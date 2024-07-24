package com.example.services;

import com.example.dto.UserDto;

import java.util.List;

public interface IUserCommandService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
}

package com.example.services;

import com.example.dto.UserDto;

import java.util.List;

public interface IUserQueryService {
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
}

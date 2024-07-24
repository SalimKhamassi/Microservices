package com.example.services;

import com.example.dto.UserDto;
import com.example.entities.User;
import com.example.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements IUserQueryService {
    private final UserRepository userRepository;

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserDto.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDto::mapToUserDto)
                .collect(Collectors.toList());
    }
}

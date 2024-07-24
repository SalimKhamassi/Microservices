package com.example.events;

import com.example.dto.UserDto;
import com.example.entities.User;
import com.example.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventHandler {
    private final UserRepository userRepository;

    public void handleUserCreatedEvent(UserDto userDto) {
        User user = UserDto.mapToUser(userDto);
        userRepository.save(user);
    }

    public void handleUserUpdatedEvent(UserDto userDto) {
        User user = userRepository.findById(userDto.id())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDto.name());
        userRepository.save(user);
    }

    public void handleUserDeletedEvent(Long userId) {
        userRepository.deleteById(userId);
    }
}
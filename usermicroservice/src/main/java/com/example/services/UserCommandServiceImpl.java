package com.example.services;

import com.example.dto.UserDto;
import com.example.entities.User;
import com.example.kafka.KafkaProducer;
import com.example.repositories.UserRepository;
import com.example.shared.EventType;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCommandServiceImpl implements IUserCommandService {
    private final UserRepository userRepository;
    private final KafkaProducer kafkaProducer;

    public UserCommandServiceImpl(UserRepository userRepository, KafkaProducer kafkaProducer) {
        this.userRepository = userRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserDto.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        UserDto savedUserDto = UserDto.mapToUserDto(savedUser);
        kafkaProducer.sendUserEvent(EventType.CREATED_USER_EVENT, savedUserDto);
        return savedUserDto;
    }



    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(userDto.name());
        User updatedUser = userRepository.save(existingUser);
        UserDto updatedUserDto = UserDto.mapToUserDto(updatedUser);
        kafkaProducer.sendUserEvent(EventType.UPDATED_USER_EVENT, updatedUserDto);
        return updatedUserDto;
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
        kafkaProducer.sendUserEvent(EventType.DELETED_USER_EVENT, new UserDto(id, null));
    }
}
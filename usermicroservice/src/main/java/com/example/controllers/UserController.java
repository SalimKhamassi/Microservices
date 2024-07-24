package com.example.controllers;

import com.example.dto.UserDto;
import com.example.services.IUserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserCommandService userCommandService;

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userCommandService.createUser(userDto);
    }



    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userCommandService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userCommandService.deleteUser(id);
    }
}

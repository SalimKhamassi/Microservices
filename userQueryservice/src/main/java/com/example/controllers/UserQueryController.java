package com.example.controllers;


import com.example.dto.UserDto;
import com.example.services.IUserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/queryusers")
@RequiredArgsConstructor
public class UserQueryController {
    private final IUserQueryService userQueryService;

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userQueryService.getUserById(id);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userQueryService.getAllUsers();
    }
}

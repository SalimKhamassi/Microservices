package com.example.dto;

public record EventWithUserDetailsDto(
        String id,
        String title,
        Long userId,
        String userName
) {}

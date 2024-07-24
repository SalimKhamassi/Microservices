package com.example.dto;

import com.example.shared.EventType;

public record Event(EventType type, UserDto userDto) {
}

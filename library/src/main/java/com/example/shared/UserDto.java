package com.example.shared;

public record UserDto(long id, String name) {
    public static User mapToUser(UserDto userDto) {
        return new User(userDto.id(), userDto.name());
    }
    public static UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getName());
    }
}

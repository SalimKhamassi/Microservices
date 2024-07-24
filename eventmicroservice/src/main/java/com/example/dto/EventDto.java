package com.example.dto;

import com.example.entities.Event;

public record EventDto(String id, String title, Long userId) {
    public static Event mapToEvent(EventDto eventDto) {
        Event event = new Event();
        event.setId(eventDto.id());
        event.setTitle(eventDto.title());
        event.setUserId(eventDto.userId());
        return event;
    }

    public static EventDto mapToEventDto(Event event) {
        return new EventDto(event.getId(), event.getTitle(), event.getUserId());
    }
}

package com.example.services;

import com.example.dto.EventDto;
import com.example.dto.EventWithUserDetailsDto;

import java.util.List;

public interface IEventService {
    EventDto createEvent(EventDto eventDto);
    EventDto getEventById(String id);
    List<EventDto> getAllEvents();
    EventDto updateEvent(String id, EventDto eventDto);
    void deleteEvent(String id);

    List<EventWithUserDetailsDto> getAllEventsWithUserDetails();
}

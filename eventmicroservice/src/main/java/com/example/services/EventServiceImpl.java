package com.example.services;

import com.example.dto.EventDto;
import com.example.dto.EventWithUserDetailsDto;
import com.example.dto.UserDto;
import com.example.entities.Event;
import com.example.entities.User;
import com.example.repositories.EventRepository;
import com.example.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final UserClient userClient;

    public List<EventWithUserDetailsDto> getAllEventsWithUserDetails() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(event -> {
                    UserDto userDetails = userClient.getUserById(event.getUserId());
                    return new EventWithUserDetailsDto(
                            event.getId(),
                            event.getTitle(),
                            event.getUserId(),
                            userDetails.name()

                    );
                })
                .collect(Collectors.toList());
    }
    @Override
    public EventDto createEvent(EventDto eventDto) {
        User user = userRepository.findById(eventDto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = EventDto.mapToEvent(eventDto);
        Event savedEvent = eventRepository.save(event);
        return EventDto.mapToEventDto(savedEvent);
    }

    @Override
    public EventDto getEventById(String id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return EventDto.mapToEventDto(event);
    }

    @Override
    public List<EventDto> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(EventDto::mapToEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDto updateEvent(String id, EventDto eventDto) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (!existingEvent.getUserId().equals(eventDto.userId())) {
            User user = userRepository.findById(eventDto.userId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }

        existingEvent.setTitle(eventDto.title());
        existingEvent.setUserId(eventDto.userId());
        Event updatedEvent = eventRepository.save(existingEvent);
        return EventDto.mapToEventDto(updatedEvent);
    }

    @Override
    public void deleteEvent(String id) {
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Event not found");
        }
        eventRepository.deleteById(id);
    }
}
package com.example.controllers;
import com.example.dto.EventWithUserDetailsDto;
import com.example.dto.UserDto;
import com.example.dto.EventDto;
import com.example.services.IEventService;
import com.example.services.UserRestTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final IEventService eventService;
    private final UserRestTemplateService userRestTemplateService;

    @PostMapping
    public EventDto createEvent(@RequestBody EventDto eventDto) {
        return eventService.createEvent(eventDto);
    }

    @GetMapping("/{id}")
    public EventDto getEvent(@PathVariable String id) {
        return eventService.getEventById(id);
    }

    @GetMapping
    public List<EventDto> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PutMapping("/{id}")
    public EventDto updateEvent(@PathVariable String id, @RequestBody EventDto eventDto) {
        return eventService.updateEvent(id, eventDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
    }

    @GetMapping("/users-rest")
    public List<UserDto> getAllUsersUsingRestTemplate() {
        return userRestTemplateService.getAllUsers();
    }

    @GetMapping("/with-user-details-opf")
    public List<EventWithUserDetailsDto> getAllEventsWithUserDetails() {
        return eventService.getAllEventsWithUserDetails();
    }
}

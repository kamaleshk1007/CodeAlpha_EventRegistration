package com.codealpha.eventregistration.controller;

import com.codealpha.eventregistration.dto.EventRequest;
import com.codealpha.eventregistration.model.Event;
import com.codealpha.eventregistration.service.EventService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping
    public Event createEvent(@Valid @RequestBody EventRequest request) {
        return eventService.createEvent(request);
    }
}
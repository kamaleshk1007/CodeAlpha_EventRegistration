package com.codealpha.eventregistration.service;

import com.codealpha.eventregistration.dto.EventRequest;
import com.codealpha.eventregistration.model.Event;
import com.codealpha.eventregistration.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event not found"));
    }

    public Event createEvent(EventRequest request) {
        Event event = new Event(
                request.getTitle(),
                request.getDescription(),
                request.getDate(),
                request.getLocation()
        );
        return eventRepository.save(event);
    }
}
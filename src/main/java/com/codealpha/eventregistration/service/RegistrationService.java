package com.codealpha.eventregistration.service;

import com.codealpha.eventregistration.dto.RegisterRequest;
import com.codealpha.eventregistration.dto.RegistrationResponse;
import com.codealpha.eventregistration.model.Event;
import com.codealpha.eventregistration.model.Registration;
import com.codealpha.eventregistration.model.User;
import com.codealpha.eventregistration.repository.EventRepository;
import com.codealpha.eventregistration.repository.RegistrationRepository;
import com.codealpha.eventregistration.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public RegistrationService(
            RegistrationRepository registrationRepository,
            UserRepository userRepository,
            EventRepository eventRepository) {
        this.registrationRepository = registrationRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional
    public RegistrationResponse register(RegisterRequest request) {
        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event not found"));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseGet(() -> userRepository.save(
                        new User(request.getName(), request.getEmail())));

        Registration registration = registrationRepository.save(
                new Registration(user, event));

        return toResponse(registration);
    }

    @Transactional(readOnly = true)
    public List<RegistrationResponse> getRegistrationsByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));

        return registrationRepository.findByUser(user)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public void cancelRegistration(Long registrationId, String email) {
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Registration not found"));

        if (!registration.getUser().getEmail().equalsIgnoreCase(email)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "You cannot cancel another user's registration");
        }

        registrationRepository.delete(registration);
    }

    private RegistrationResponse toResponse(Registration registration) {
        return new RegistrationResponse(
                registration.getId(),
                registration.getUser().getName(),
                registration.getUser().getEmail(),
                registration.getEvent().getId(),
                registration.getEvent().getTitle(),
                registration.getEvent().getDate(),
                registration.getEvent().getLocation(),
                registration.getRegistrationDate()
        );
    }
}
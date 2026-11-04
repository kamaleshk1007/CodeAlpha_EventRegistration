package com.codealpha.eventregistration.dto;

import java.time.LocalDateTime;

public record RegistrationResponse(
        Long registrationId,
        String userName,
        String userEmail,
        Long eventId,
        String eventTitle,
        LocalDateTime eventDate,
        String eventLocation,
        LocalDateTime registrationDate
) {}
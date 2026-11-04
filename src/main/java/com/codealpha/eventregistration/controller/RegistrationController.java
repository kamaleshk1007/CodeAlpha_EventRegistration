package com.codealpha.eventregistration.controller;

import com.codealpha.eventregistration.dto.RegisterRequest;
import com.codealpha.eventregistration.dto.RegistrationResponse;
import com.codealpha.eventregistration.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public RegistrationResponse register(@Valid @RequestBody RegisterRequest request) {
        return registrationService.register(request);
    }

    @GetMapping
    public List<RegistrationResponse> getRegistrations(@RequestParam String email) {
        return registrationService.getRegistrationsByEmail(email);
    }

    @DeleteMapping("/{id}")
    public void cancelRegistration(
            @PathVariable Long id,
            @RequestParam String email) {
        registrationService.cancelRegistration(id, email);
    }
}
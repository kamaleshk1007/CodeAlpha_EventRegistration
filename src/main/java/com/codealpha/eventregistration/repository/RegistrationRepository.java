package com.codealpha.eventregistration.repository;

import com.codealpha.eventregistration.model.Registration;
import com.codealpha.eventregistration.model.User;
import com.codealpha.eventregistration.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    List<Registration> findByUser(User user);

    List<Registration> findByEvent(Event event);
}

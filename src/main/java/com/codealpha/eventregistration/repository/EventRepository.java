package com.codealpha.eventregistration.repository;

import com.codealpha.eventregistration.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}

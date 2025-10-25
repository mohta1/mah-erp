package com.mah.hrservice.repository;

import com.mah.hrservice.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}

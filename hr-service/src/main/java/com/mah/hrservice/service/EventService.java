package com.mah.hrservice.service;

import com.mah.hrservice.dto.EventDTO;
import java.util.List;
import java.util.UUID;

public interface EventService {
    EventDTO createEvent(EventDTO eventDTO);
    EventDTO getEventById(UUID id);
    List<EventDTO> getAllEvents();
}

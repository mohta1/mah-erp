package com.mah.hrservice.service.impl;

import com.mah.hrservice.domain.Event;
import com.mah.hrservice.dto.EventDTO;
import com.mah.hrservice.mapper.HRMapper;
import com.mah.hrservice.repository.EventRepository;
import com.mah.hrservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final HRMapper hrMapper;

    @Override
    public EventDTO createEvent(EventDTO dto) {
        Event event = hrMapper.toEvent(dto);
        Event saved = eventRepository.save(event);
        return hrMapper.toEventDTO(saved);
    }

    @Override
    public EventDTO getEventById(UUID id) {
        return eventRepository.findById(id)
                .map(hrMapper::toEventDTO)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @Override
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(hrMapper::toEventDTO)
                .collect(Collectors.toList());
    }
}

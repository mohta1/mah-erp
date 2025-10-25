package com.mah.hrservice.service.impl;

import com.mah.hrservice.domain.Employee;
import com.mah.hrservice.domain.EmployeeEventAssignment;
import com.mah.hrservice.domain.Event;
import com.mah.hrservice.dto.EmployeeEventAssignmentDTO;
import com.mah.hrservice.mapper.HRMapper;
import com.mah.hrservice.repository.EmployeeEventAssignmentRepository;
import com.mah.hrservice.repository.EmployeeRepository;
import com.mah.hrservice.repository.EventRepository;
import com.mah.hrservice.service.EmployeeEventAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeEventAssignmentServiceImpl implements EmployeeEventAssignmentService {

    private final EmployeeEventAssignmentRepository assignmentRepository;
    private final EmployeeRepository employeeRepository;
    private final EventRepository eventRepository;
    private final HRMapper hrMapper;

    @Override
    public EmployeeEventAssignmentDTO assignEmployeeToEvent(EmployeeEventAssignmentDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Event event = eventRepository.findById(dto.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Employee assignedBy = employeeRepository.findById(dto.getAssignedById())
                .orElseThrow(() -> new RuntimeException("Assigning manager not found"));

        EmployeeEventAssignment assignment = EmployeeEventAssignment.builder()
                .employee(employee)
                .event(event)
                .assignedBy(assignedBy)
                .build();

        EmployeeEventAssignment saved = assignmentRepository.save(assignment);
        return hrMapper.toAssignmentDTO(saved);
    }

    @Override
    public List<EmployeeEventAssignmentDTO> getAssignmentsByEmployee(UUID employeeId) {
        return assignmentRepository.findAll().stream()
                .filter(a -> a.getEmployee().getId().equals(employeeId))
                .map(hrMapper::toAssignmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeEventAssignmentDTO> getAssignmentsByEvent(UUID eventId) {
        return assignmentRepository.findAll().stream()
                .filter(a -> a.getEvent().getId().equals(eventId))
                .map(hrMapper::toAssignmentDTO)
                .collect(Collectors.toList());
    }
}

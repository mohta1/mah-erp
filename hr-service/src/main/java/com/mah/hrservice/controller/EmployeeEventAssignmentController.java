package com.mah.hrservice.controller;

import com.mah.hrservice.dto.EmployeeEventAssignmentDTO;
import com.mah.hrservice.service.EmployeeEventAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class EmployeeEventAssignmentController {

    private final EmployeeEventAssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<EmployeeEventAssignmentDTO> assignEmployee(@RequestBody EmployeeEventAssignmentDTO dto) {
        return ResponseEntity.ok(assignmentService.assignEmployeeToEvent(dto));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeEventAssignmentDTO>> getByEmployee(@PathVariable UUID employeeId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByEmployee(employeeId));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<EmployeeEventAssignmentDTO>> getByEvent(@PathVariable UUID eventId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByEvent(eventId));
    }
}

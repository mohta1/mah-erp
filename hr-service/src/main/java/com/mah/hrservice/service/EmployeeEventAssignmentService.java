package com.mah.hrservice.service;

import com.mah.hrservice.dto.EmployeeEventAssignmentDTO;
import java.util.List;
import java.util.UUID;

public interface EmployeeEventAssignmentService {
    EmployeeEventAssignmentDTO assignEmployeeToEvent(EmployeeEventAssignmentDTO dto);
    List<EmployeeEventAssignmentDTO> getAssignmentsByEmployee(UUID employeeId);
    List<EmployeeEventAssignmentDTO> getAssignmentsByEvent(UUID eventId);
}

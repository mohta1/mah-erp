package com.mah.hrservice.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeEventAssignmentDTO {
    private UUID id;
    private UUID employeeId;
    private UUID eventId;
    private UUID assignedById;
}

package com.mah.hrservice.dto;

import com.mah.hrservice.enums.ApprovalStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkTimeDTO {
    private UUID id;
    private UUID employeeId;
    private UUID eventId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int breakMinutes;
    private double hourlyRate;
    private double calculatedSalary;
    private ApprovalStatus managerApproval;
}

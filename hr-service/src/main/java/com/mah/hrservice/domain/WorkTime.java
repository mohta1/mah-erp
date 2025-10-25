package com.mah.hrservice.domain;

import com.mah.hrservice.enums.ApprovalStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "work_times")
public class WorkTime {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private LocalDate date;

    private LocalTime startTime;
    private LocalTime endTime;

    private int breakMinutes;

    private double hourlyRate;
    private double calculatedSalary;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus managerApproval = ApprovalStatus.PENDING;
}

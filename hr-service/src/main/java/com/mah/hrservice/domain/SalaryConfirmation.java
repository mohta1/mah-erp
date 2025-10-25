package com.mah.hrservice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.YearMonth;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "salary_confirmations")
public class SalaryConfirmation {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private YearMonth month;

    private double totalSalary;

    private boolean employeeConfirmed = false;

    private boolean managerConfirmed = false;
}

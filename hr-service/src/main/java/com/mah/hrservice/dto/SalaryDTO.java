package com.mah.hrservice.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryDTO {
    private UUID employeeId;
    private String employeeName;
    private double totalSalary;
}

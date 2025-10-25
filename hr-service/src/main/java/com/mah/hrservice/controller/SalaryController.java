package com.mah.hrservice.controller;

import com.mah.hrservice.dto.SalaryDTO;
import com.mah.hrservice.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/salaries")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService salaryService;

    @GetMapping("/month")
    public ResponseEntity<List<SalaryDTO>> getMonthlySalaries(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        return ResponseEntity.ok(salaryService.calculateMonthlySalary(month));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<SalaryDTO> getEmployeeSalary(
            @PathVariable UUID employeeId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        return ResponseEntity.ok(salaryService.getEmployeeSalaryForMonth(employeeId, month));
    }
}

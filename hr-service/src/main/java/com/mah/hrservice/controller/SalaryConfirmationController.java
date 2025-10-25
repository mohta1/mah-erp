package com.mah.hrservice.controller;

import com.mah.hrservice.domain.SalaryConfirmation;
import com.mah.hrservice.service.SalaryConfirmationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/salary-confirmations")
@RequiredArgsConstructor
public class SalaryConfirmationController {

    private final SalaryConfirmationService service;

    @PostMapping("/create")
    public ResponseEntity<SalaryConfirmation> createOrUpdate(
            @RequestParam UUID employeeId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month,
            @RequestParam double totalSalary) {
        return ResponseEntity.ok(service.createOrUpdate(employeeId, month, totalSalary));
    }

    @PostMapping("/employee-confirm")
    public ResponseEntity<SalaryConfirmation> employeeConfirm(
            @RequestParam UUID employeeId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        return ResponseEntity.ok(service.employeeConfirm(employeeId, month));
    }

    @PostMapping("/manager-confirm")
    public ResponseEntity<SalaryConfirmation> managerConfirm(
            @RequestParam UUID employeeId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        return ResponseEntity.ok(service.managerConfirm(employeeId, month));
    }

    @GetMapping("/month")
    public ResponseEntity<List<SalaryConfirmation>> getMonthlyConfirmations(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        return ResponseEntity.ok(service.getMonthlyConfirmations(month));
    }
}

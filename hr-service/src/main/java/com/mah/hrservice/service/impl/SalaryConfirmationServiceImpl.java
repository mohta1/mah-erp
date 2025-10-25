package com.mah.hrservice.service.impl;

import com.mah.hrservice.domain.Employee;
import com.mah.hrservice.domain.SalaryConfirmation;
import com.mah.hrservice.repository.EmployeeRepository;
import com.mah.hrservice.repository.SalaryConfirmationRepository;
import com.mah.hrservice.service.SalaryConfirmationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SalaryConfirmationServiceImpl implements SalaryConfirmationService {

    private final SalaryConfirmationRepository repository;
    private final EmployeeRepository employeeRepository;

    @Override
    public SalaryConfirmation createOrUpdate(UUID employeeId, YearMonth month, double totalSalary) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        SalaryConfirmation confirmation = repository.findByEmployeeIdAndMonth(employeeId, month)
                .orElse(SalaryConfirmation.builder()
                        .employee(employee)
                        .month(month)
                        .build());

        confirmation.setTotalSalary(totalSalary);
        return repository.save(confirmation);
    }

    @Override
    public SalaryConfirmation employeeConfirm(UUID employeeId, YearMonth month) {
        SalaryConfirmation confirmation = repository.findByEmployeeIdAndMonth(employeeId, month)
                .orElseThrow(() -> new RuntimeException("Salary confirmation not found"));

        confirmation.setEmployeeConfirmed(true);
        return repository.save(confirmation);
    }

    @Override
    public SalaryConfirmation managerConfirm(UUID employeeId, YearMonth month) {
        SalaryConfirmation confirmation = repository.findByEmployeeIdAndMonth(employeeId, month)
                .orElseThrow(() -> new RuntimeException("Salary confirmation not found"));

        confirmation.setManagerConfirmed(true);
        return repository.save(confirmation);
    }

    @Override
    public List<SalaryConfirmation> getMonthlyConfirmations(YearMonth month) {
        return repository.findByMonth(month);
    }
}

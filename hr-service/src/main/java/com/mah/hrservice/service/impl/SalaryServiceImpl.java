package com.mah.hrservice.service.impl;

import com.mah.hrservice.domain.WorkTime;
import com.mah.hrservice.dto.SalaryDTO;
import com.mah.hrservice.repository.EmployeeRepository;
import com.mah.hrservice.repository.WorkTimeRepository;
import com.mah.hrservice.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private final WorkTimeRepository workTimeRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<SalaryDTO> calculateMonthlySalary(YearMonth month) {
        return employeeRepository.findAll().stream()
                .map(emp -> {
                    double total = workTimeRepository.findByEmployeeId(emp.getId()).stream()
                            .filter(wt -> wt.getManagerApproval() != null &&
                                          wt.getManagerApproval().name().equals("APPROVED") &&
                                          YearMonth.from(wt.getDate()).equals(month))
                            .mapToDouble(WorkTime::getCalculatedSalary)
                            .sum();
                    return SalaryDTO.builder()
                            .employeeId(emp.getId())
                            .employeeName(emp.getFirstName() + " " + emp.getLastName())
                            .totalSalary(total)
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public SalaryDTO getEmployeeSalaryForMonth(UUID employeeId, YearMonth month) {
        var emp = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        double total = workTimeRepository.findByEmployeeId(emp.getId()).stream()
                .filter(wt -> wt.getManagerApproval() != null &&
                              wt.getManagerApproval().name().equals("APPROVED") &&
                              YearMonth.from(wt.getDate()).equals(month))
                .mapToDouble(WorkTime::getCalculatedSalary)
                .sum();

        return SalaryDTO.builder()
                .employeeId(emp.getId())
                .employeeName(emp.getFirstName() + " " + emp.getLastName())
                .totalSalary(total)
                .build();
    }
}

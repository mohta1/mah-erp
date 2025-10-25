package com.mah.hrservice.service;

import com.mah.hrservice.dto.SalaryDTO;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

public interface SalaryService {

    List<SalaryDTO> calculateMonthlySalary(YearMonth month);

    SalaryDTO getEmployeeSalaryForMonth(UUID employeeId, YearMonth month);
}

package com.mah.hrservice.service;

import com.mah.hrservice.domain.SalaryConfirmation;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

public interface SalaryConfirmationService {

    SalaryConfirmation createOrUpdate(UUID employeeId, YearMonth month, double totalSalary);

    SalaryConfirmation employeeConfirm(UUID employeeId, YearMonth month);

    SalaryConfirmation managerConfirm(UUID employeeId, YearMonth month);

    List<SalaryConfirmation> getMonthlyConfirmations(YearMonth month);
}

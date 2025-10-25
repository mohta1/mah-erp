package com.mah.hrservice.repository;

import com.mah.hrservice.domain.SalaryConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SalaryConfirmationRepository extends JpaRepository<SalaryConfirmation, UUID> {

    Optional<SalaryConfirmation> findByEmployeeIdAndMonth(UUID employeeId, YearMonth month);

    List<SalaryConfirmation> findByMonth(YearMonth month);
}

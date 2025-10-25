package com.mah.hrservice.repository;

import com.mah.hrservice.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findByNationalId(String nationalId);
}

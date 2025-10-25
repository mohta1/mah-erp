package com.mah.hrservice.repository;

import com.mah.hrservice.domain.EmployeeEventAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EmployeeEventAssignmentRepository extends JpaRepository<EmployeeEventAssignment, UUID> {
}

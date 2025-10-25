package com.mah.hrservice.repository;

import com.mah.hrservice.domain.WorkTime;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface WorkTimeRepository extends JpaRepository<WorkTime, UUID> {
    List<WorkTime> findByEmployeeId(UUID employeeId);
    List<WorkTime> findByEventId(UUID eventId);
}

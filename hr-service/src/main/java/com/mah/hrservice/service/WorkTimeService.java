package com.mah.hrservice.service;

import com.mah.hrservice.dto.WorkTimeDTO;

import java.util.List;
import java.util.UUID;

public interface WorkTimeService {

    WorkTimeDTO registerWorkTime(WorkTimeDTO dto);

    WorkTimeDTO approveWorkTime(UUID workTimeId, boolean approve);

    List<WorkTimeDTO> getWorkTimesByEmployee(UUID employeeId);

    List<WorkTimeDTO> getWorkTimesByEvent(UUID eventId);
}

package com.mah.hrservice.service.impl;

import com.mah.hrservice.domain.Employee;
import com.mah.hrservice.domain.Event;
import com.mah.hrservice.domain.WorkTime;
import com.mah.hrservice.dto.WorkTimeDTO;
import com.mah.hrservice.enums.ApprovalStatus;
import com.mah.hrservice.mapper.HRMapper;
import com.mah.hrservice.repository.EmployeeRepository;
import com.mah.hrservice.repository.EventRepository;
import com.mah.hrservice.repository.WorkTimeRepository;
import com.mah.hrservice.service.WorkTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkTimeServiceImpl implements WorkTimeService {

    private final WorkTimeRepository workTimeRepository;
    private final EmployeeRepository employeeRepository;
    private final EventRepository eventRepository;
    private final HRMapper hrMapper;

    @Override
    public WorkTimeDTO registerWorkTime(WorkTimeDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Event event = eventRepository.findById(dto.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        long workedMinutes = Duration.between(dto.getStartTime(), dto.getEndTime()).toMinutes() - dto.getBreakMinutes();
        double salary = (workedMinutes / 60.0) * dto.getHourlyRate();

        WorkTime workTime = WorkTime.builder()
                .employee(employee)
                .event(event)
                .date(dto.getDate())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .breakMinutes(dto.getBreakMinutes())
                .hourlyRate(dto.getHourlyRate())
                .calculatedSalary(salary)
                .managerApproval(ApprovalStatus.PENDING)
                .build();

        WorkTime saved = workTimeRepository.save(workTime);
        return hrMapper.toWorkTimeDTO(saved);
    }

    @Override
    public WorkTimeDTO approveWorkTime(UUID workTimeId, boolean approve) {
        WorkTime workTime = workTimeRepository.findById(workTimeId)
                .orElseThrow(() -> new RuntimeException("WorkTime not found"));

        workTime.setManagerApproval(approve ? ApprovalStatus.APPROVED : ApprovalStatus.REJECTED);
        WorkTime saved = workTimeRepository.save(workTime);
        return hrMapper.toWorkTimeDTO(saved);
    }

    @Override
    public List<WorkTimeDTO> getWorkTimesByEmployee(UUID employeeId) {
        return workTimeRepository.findByEmployeeId(employeeId).stream()
                .map(hrMapper::toWorkTimeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkTimeDTO> getWorkTimesByEvent(UUID eventId) {
        return workTimeRepository.findByEventId(eventId).stream()
                .map(hrMapper::toWorkTimeDTO)
                .collect(Collectors.toList());
    }
}

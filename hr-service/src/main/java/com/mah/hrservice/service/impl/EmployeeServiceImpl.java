package com.mah.hrservice.service.impl;

import com.mah.hrservice.domain.Employee;
import com.mah.hrservice.dto.EmployeeDTO;
import com.mah.hrservice.mapper.HRMapper;
import com.mah.hrservice.repository.EmployeeRepository;
import com.mah.hrservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final HRMapper hrMapper;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee employee = hrMapper.toEmployee(dto);
        Employee saved = employeeRepository.save(employee);
        return hrMapper.toEmployeeDTO(saved);
    }

    @Override
    public EmployeeDTO getEmployeeById(UUID id) {
        return employeeRepository.findById(id)
                .map(hrMapper::toEmployeeDTO)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(hrMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }
}

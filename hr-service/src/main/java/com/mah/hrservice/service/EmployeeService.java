package com.mah.hrservice.service;

import com.mah.hrservice.dto.EmployeeDTO;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO getEmployeeById(UUID id);
    List<EmployeeDTO> getAllEmployees();
}

package com.mah.hrservice.dto;

import com.mah.hrservice.enums.Role;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private UUID id;
    private String nationalId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Role role;
}

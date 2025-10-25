
package com.mah.hrservice.mapper;

import com.mah.hrservice.domain.Employee;
import com.mah.hrservice.domain.EmployeeEventAssignment;
import com.mah.hrservice.domain.Event;
import com.mah.hrservice.domain.WorkTime;
import com.mah.hrservice.dto.EmployeeDTO;
import com.mah.hrservice.dto.EmployeeEventAssignmentDTO;
import com.mah.hrservice.dto.EventDTO;
import com.mah.hrservice.dto.WorkTimeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HRMapper {
    HRMapper INSTANCE = Mappers.getMapper(HRMapper.class);

    EmployeeDTO toEmployeeDTO(Employee employee);
    Employee toEmployee(EmployeeDTO dto);

    EventDTO toEventDTO(Event event);
    Event toEvent(EventDTO dto);

    EmployeeEventAssignmentDTO toAssignmentDTO(EmployeeEventAssignment assignment);
    EmployeeEventAssignment toAssignment(EmployeeEventAssignmentDTO dto);

    WorkTimeDTO toWorkTimeDTO(WorkTime workTime);
    WorkTime toWorkTime(WorkTimeDTO dto);
}


package com.mah.hrservice.controller;

import com.mah.hrservice.dto.WorkTimeDTO;
import com.mah.hrservice.service.WorkTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/worktimes")
@RequiredArgsConstructor
public class WorkTimeController {

    private final WorkTimeService workTimeService;

    @PostMapping
    public ResponseEntity<WorkTimeDTO> registerWorkTime(@RequestBody WorkTimeDTO dto) {
        return ResponseEntity.ok(workTimeService.registerWorkTime(dto));
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<WorkTimeDTO> approveWorkTime(@PathVariable UUID id,
                                                       @RequestParam boolean approve) {
        return ResponseEntity.ok(workTimeService.approveWorkTime(id, approve));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<WorkTimeDTO>> getByEmployee(@PathVariable UUID employeeId) {
        return ResponseEntity.ok(workTimeService.getWorkTimesByEmployee(employeeId));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<WorkTimeDTO>> getByEvent(@PathVariable UUID eventId) {
        return ResponseEntity.ok(workTimeService.getWorkTimesByEvent(eventId));
    }
}

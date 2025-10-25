package com.mah.hrservice.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDTO {
    private UUID id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
}

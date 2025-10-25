package com.mah.notification.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SMSRequest {
    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String message;
}

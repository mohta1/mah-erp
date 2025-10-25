package com.mah.notification.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelegramRequest {
    @NotBlank
    private String chatId;

    @NotBlank
    private String message;
}

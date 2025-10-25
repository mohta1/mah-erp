package com.mah.notification.controller;

import com.mah.notification.dto.EmailRequest;
import com.mah.notification.dto.TelegramRequest;
import com.mah.notification.dto.SMSRequest;
import com.mah.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(@Valid @RequestBody EmailRequest request) {
        notificationService.sendEmail(request);
        return ResponseEntity.ok("Email sent successfully");
    }

    @PostMapping("/telegram")
    public ResponseEntity<String> sendTelegram(@Valid @RequestBody TelegramRequest request) {
        notificationService.sendTelegram(request);
        return ResponseEntity.ok("Telegram message sent successfully");
    }

    @PostMapping("/sms")
    public ResponseEntity<String> sendSMS(@Valid @RequestBody SMSRequest request) {
        notificationService.sendSMS(request);
        return ResponseEntity.ok("SMS sent successfully");
    }
}

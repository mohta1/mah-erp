package com.mah.notification.service.impl;

import com.mah.notification.dto.EmailRequest;
import com.mah.notification.dto.TelegramRequest;
import com.mah.notification.dto.SMSRequest;
import com.mah.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(EmailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getBody());
        mailSender.send(message);
    }

    @Override
    public void sendTelegram(TelegramRequest request) {
        // TODO: Implement this
        System.out.println("Sending Telegram message to " + request.getChatId() + ": " + request.getMessage());
    }

    @Override
    public void sendSMS(SMSRequest request) {
        // TODO: Implement this
        System.out.println("Sending SMS to " + request.getPhoneNumber() + ": " + request.getMessage());
    }
}

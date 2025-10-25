package com.mah.notification.service;

import com.mah.notification.dto.EmailRequest;
import com.mah.notification.dto.SMSRequest;
import com.mah.notification.dto.TelegramRequest;

public interface NotificationService {
    void sendEmail(EmailRequest emailRequest);

    void sendTelegram(TelegramRequest request);

    void sendSMS(SMSRequest request);
}

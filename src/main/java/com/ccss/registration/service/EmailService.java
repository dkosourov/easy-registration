package com.ccss.registration.service;

import com.ccss.registration.email.Mail;

public interface EmailService {
    void sendSimpleMessage(Mail mail);
}
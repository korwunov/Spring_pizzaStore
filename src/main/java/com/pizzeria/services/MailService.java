package com.pizzeria.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailService extends SimpleMailMessage {
    @Autowired
    public JavaMailSender emailSender;

    @Async
    public void sendRegistrationEmail() {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo("korwunov.petr@yandex.ru");
        smm.setSubject("Регистрация в системе");
        smm.setText(
                "Здравствуйте, благодарим вас за регистраицю в системе!\n" +
                        "Ваш логин: ");
        emailSender.send(smm);
        log.info("EMAIL SENDED");
    }
}

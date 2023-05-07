package com.pizzeria.services;

import com.pizzeria.entity.classes.Order;
import com.pizzeria.entity.classes.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService extends SimpleMailMessage {
    @Autowired
    public JavaMailSender emailSender;

    @Async
    public void sendRegistrationEmail(User u) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(System.getenv("MAIL_USERNAME"));
        smm.setTo(u.getEmail());
        smm.setSubject("Регистрация в системе");
        smm.setText(
                "Здравствуйте, " + u.getFirstName() + ", благодарим вас за регистраицю в системе!\n" +
                        "Ваш логин: " + u.getEmail());
        emailSender.send(smm);
        log.info("REGISTRATION EMAIL SENDED TO: " + u.getEmail());
    }

    @Async
    public void sendNewOrderEmail(User u, Order o) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(System.getenv("MAIL_USERNAME"));
        smm.setTo(u.getEmail());
        smm.setSubject("Новый заказ");
        smm.setText(
                "Здравствуйте, " + u.getFirstName() + ", вы только что сделали новый заказ!\n" +
                        "Номер заказа: " + o.getID() + ", адрес доставки: " + o.getAddress() +
                        "\nОтслеживать статус заказа можно на сайте в разделе Мои заказы");
        emailSender.send(smm);
        log.info("NEW ORDER EMAIL SENDED TO: " + u.getEmail() + " ORDER ID: " + o.getID());
    }

    @Async
    public void sendChangeOrderStatusEmail(User u, Order o) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(System.getenv("MAIL_USERNAME"));
        smm.setTo(u.getEmail());
        smm.setSubject("Новый статус заказа");
        smm.setText(
                "Здравствуйте, " + u.getFirstName() + ", статус вашего заказа изменён!\n" +
                        "Заказ №: " + o.getID() + ", текущий статус: " + o.getStatus().name() +
                        "\nОтслеживать статус заказа можно на сайте в разделе Мои заказы");
        emailSender.send(smm);
        log.info("CANCEL ORDER EMAIL SENDED TO: " + u.getEmail() + " ORDER ID: " + o.getID());
    }

    @Async
    public void sendCancelOrderEmail(User u, Order o) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(System.getenv("MAIL_USERNAME"));
        smm.setTo(u.getEmail());
        smm.setSubject("Отмена заказа");
        smm.setText(
                "Здравствуйте, " + u.getFirstName() + ", ваш заказ №" + o.getID() + " был отменен!\n" +
                        "\nДля нового заказа посетите раздел Товары на нашем сайте.");
        emailSender.send(smm);
        log.info("CANCEL ORDER EMAIL SENDED TO: " + u.getEmail() + " ORDER ID: " + o.getID());
    }
}

package com.pizzeria.aspects;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MailServiceAspect {
    @After("sendRegistrationEmail()")
    public void send() {
//        MailService service = new MailService();
//        service.sendRegistrationEmail();
        log.info("REGISTRATION MAIL SENDED TO ");
    }

    @Pointcut("within(com.lab18.services.*)")
    public void sendRegistrationEmail() {}
}

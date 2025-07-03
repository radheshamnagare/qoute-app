package com.radhesham.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);
    @Autowired
    JavaMailSender mailSender;

    public boolean sendEmail(String toAddress, String ccAddress,String subject ,String msgText) {
        boolean flag=true;
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("radheshamnagare@gamil.com");
            message.setTo(toAddress);
            message.setCc(ccAddress);
            message.setSubject(subject);
            message.setText(msgText);

            mailSender.send(message);
        }catch (Exception e){
            logger.error("Exception in sendEmail() :",e);
            flag=false;
        }
        return flag;
    }
}

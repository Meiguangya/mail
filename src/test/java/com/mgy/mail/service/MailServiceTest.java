package com.mgy.mail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    MailService mailService;

    @Test
    public void sayHiTest(){
        mailService.sayHi();
    }

    @Test
    public void sendSimpleMailTest(){
        String to="1264348585@qq.com";
        String subject="发邮件了...";
        String content="我在用Springboot给你发邮件";
        mailService.sendSimpleMail(to,subject,content);
    }

    @Test
    public void sendHtmlMailTest(){
        String to="1264348585@qq.com";
        String subject="This is a html mail";
        String content="......";
        try {
            mailService.sendHtmlMail(to,subject,content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}

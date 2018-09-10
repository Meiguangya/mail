package com.mgy.mail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    MailService mailService;

    @Autowired
    TemplateEngine templateEngine;

    @Test
    public void sayHiTest() {
        mailService.sayHi();
    }

    @Test
    public void templateMailTest(){
        Context context = new Context();
        context.setVariable("id","1");

        String emailContent = templateEngine.process("emailTemplate",context);

        String to = "15871737166@163.com";
        String subject = "发邮件了...";
        try {
            mailService.sendHtmlMail(to,subject,emailContent);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendInlinReasourceMailTest() {
        String to = "15871737166@163.com";
        String subject = "发邮件了...";
        String resPath = "C:\\Users\\meiguangya\\Pictures\\pic\\大圣.jpg";

        String resId = "img001";

        String content = "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <img src=\'cid:"+resId+"\'/>\n" +
                "</body>\n" +
                "</html>";
        try {
            mailService.sendInlinReasourceMail(to, subject, content, resPath, resId);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendAttachmentMailTest() {
        String to = "15871737166@163.com";
        String subject = "发邮件了...";
        String content = "我在用Springboot给你发邮件";
        String filePath = "C:\\Users\\meiguangya\\Pictures\\pic\\楪祈.jpg";

        try {
            mailService.sendAttachmentMail(to, subject, content, filePath);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendSimpleMailTest() {
        String to = "1264348585@qq.com";
        String subject = "发邮件了...";
        String content = "我在用Springboot给你发邮件";
        mailService.sendSimpleMail(to, subject, content);
    }

    @Test
    public void sendHtmlMailTest() {
        String to = "1264348585@qq.com";
        String subject = "This is a html mail";
        String content = "......";
        try {
            mailService.sendHtmlMail(to, subject, content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}

package com.mgy.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import sun.management.FileSystem;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.File;

/**
 * @author meiguangya
 */
@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送附件邮件
     *
     * @param to      接收人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    public void sendAttachmentMail(String to, String subject, String content, String filePath) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));

        helper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

        helper.setFrom(from);
        javaMailSender.send(message);
    }

    /**
     * 发送html邮件
     *
     * @param to      接收人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content);
        helper.setFrom(from);
        javaMailSender.send(message);
    }

    /**
     * 发送普通文本邮件
     *
     * @param to      接收人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);

        javaMailSender.send(message);
    }

    public void sayHi() {
        System.out.println(from);
    }
}

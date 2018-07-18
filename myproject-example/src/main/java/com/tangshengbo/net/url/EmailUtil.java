package com.tangshengbo.net.url;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by TangShengBo on 2018/5/4.
 */
public class EmailUtil {

    private static final String HOST = "smtp.qq.com";
    private static final Integer PORT = 25;
    private static final String USER_NAME = "1727185965@qq.com";
    private static final String PASSWORD = "qafhluwqqjbdegbe";
    private static final String TO = "867850520@qq.com";
    private static Properties prop;
    private static Mailer mailer;

    static {
        prop = System.getProperties();
        prop.put("mail.smtp.host", HOST);
        prop.put("mail.smtp.port", PORT);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.socketFactory.port", "465");
        mailer = MailerBuilder
                .withSMTPServer(HOST, PORT, USER_NAME, PASSWORD)
                .withSessionTimeout(10 * 1000)
                .clearEmailAddressCriteria()
                .withDebugLogging(false)
                .buildMailer();
    }

    /**
     * 发送文本邮件
     *
     * @throws Exception
     */
    public static void sendTextMail(String text) throws Exception {
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(prop, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USER_NAME, PASSWORD);
                    }
                });
        // 根据session创建一个邮件消息
        Message mailMessage = new MimeMessage(sendMailSession);
        // 设置邮件消息的发送者
        mailMessage.setFrom(new InternetAddress(USER_NAME));
        // 创建邮件的接收者地址，并设置到邮件消息中
        mailMessage.setRecipient(Message.RecipientType.TO,
                new InternetAddress(TO));
        // 设置邮件消息的主题
        mailMessage.setSubject("重置密码");
        // 设置邮件消息发送的时间
        mailMessage.setSentDate(new Date());
        // 设置邮件消息的主要内容
        mailMessage.setText("您的验证码为:" + text);
        // 发送邮件
        Transport.send(mailMessage);
        System.out.println("邮件已发送..........");
    }

    public static void sendEmail(List<String> to, List<String> cc, String subject, String content) throws IOException {
        Email email = EmailBuilder.startingBlank()
                .from(null, USER_NAME)
                .to(null, to)
                .cc(null, cc)
                .withSubject(subject)
                .withPlainText(content)
                .buildEmail();
        mailer.sendMail(email);
    }

    public static void sendEmailWithAttachment(List<String> to, List<String> cc, String subject, String content,
                                               String attachmentName, byte[] attachmentData, String mimetype) throws IOException {
        Email email = EmailBuilder.startingBlank()
                .from(null, USER_NAME)
                .to(null, to)
                .cc(null, cc)
                .withSubject(subject)
                .withPlainText(content)
                .withAttachment(attachmentName, attachmentData, mimetype)
                .buildEmail();
        mailer.sendMail(email);
    }


    public static void main(String[] args) {
        try {
            EmailUtil.sendTextMail("568792");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package fr.tde.mailservice.services;

import fr.tde.mailservice.controllers.requests.MailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailService {

    public static final String INVOICE = "Invoice";

    @Autowired
    private JavaMailSender emailSender;

    public void sendMail(MailRequest mailRequest) throws MessagingException {

        if(mailRequest.getPathToAttachment() != null && !"".equals(mailRequest.getPathToAttachment())) {
            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(mailRequest.getTo());
            helper.setCc(mailRequest.getCc());
            helper.setBcc(mailRequest.getBcc());
            helper.setSubject(mailRequest.getSubject());
            helper.setText(mailRequest.getText());

            FileSystemResource file = new FileSystemResource(new File(mailRequest.getPathToAttachment()));
            helper.addAttachment(INVOICE, file);
            emailSender.send(message);
        }
        else {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(mailRequest.getTo());
            simpleMailMessage.setCc(mailRequest.getCc());
            simpleMailMessage.setBcc(mailRequest.getBcc());
            simpleMailMessage.setSubject(mailRequest.getSubject());
            simpleMailMessage.setText(mailRequest.getText());

            emailSender.send(simpleMailMessage);
        }

    }

}

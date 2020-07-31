package fr.tde.mailservice.controllers;

import fr.tde.mailservice.controllers.requests.MailRequest;
import fr.tde.mailservice.controllers.responses.BooleanResponse;
import fr.tde.mailservice.services.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;

@Slf4j
@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public ResponseEntity<Boolean> send(@RequestBody MailRequest mailRequest) {

        try {
            mailService.sendMail(mailRequest);
            return ResponseEntity.ok(true);
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body(false);
        }

    }

}

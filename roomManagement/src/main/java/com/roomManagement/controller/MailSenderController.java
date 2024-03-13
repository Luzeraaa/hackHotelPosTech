package com.roomManagement.controller;


import com.roomManagement.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roomManagement")
public class MailSenderController {
    @Autowired
    private final MailService mailService;

    public MailSenderController(final MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/mailSender")
    public ResponseEntity<String> mailSender(@RequestBody final Object object) {
        final String to = "angelo.cvti@gmail.com";
        final String subject = "Assunto do e-mail";
        final String body = "Conteúdo do e-mail";

        mailService.sendEmail(to, subject, body);

        return null;
    }
}

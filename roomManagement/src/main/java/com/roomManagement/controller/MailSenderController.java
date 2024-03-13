package com.roomManagement.controller;


import com.roomManagement.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("roomManagement")
public class MailSenderController {
    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    private final MailService mailService;

    public MailSenderController(final MailService mailService, final RestTemplate restTemplate) {
        this.mailService = mailService;
        this.restTemplate = restTemplate;
    }

    //Sistema envia o Email ao cliente com os dados da Reserva.
    @GetMapping("/mailSender/userId")
    public ResponseEntity<String> mailSender(@PathVariable final Long userId) {
        final String to = "angelo.cvti@gmail.com";
        final String subject = "Assunto do e-mail";
        final String body = "Conteúdo do e-mail";

        mailService.sendEmail(to, subject, body);

        return null;
    }
}

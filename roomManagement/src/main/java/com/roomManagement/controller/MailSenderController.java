package com.roomManagement.controller;


import com.roomManagement.dto.UserResponse;
import com.roomManagement.service.MailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Tag(name = "MailSenderController")
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
    public ResponseEntity<Void> mailSender(@PathVariable final Long userId) {
        final UserResponse[] userResponse = restTemplate.getForObject("localhost:8080/api/user?id=" + userId, UserResponse[].class);

        assert userResponse != null;

        final String subject = "Booking confirmation for " + userResponse[0].getName();

        final String body = "Your booking has been confirmed. Enjoy.";

        mailService.sendEmail(userResponse[0].getEmail(), subject, body);

        return ResponseEntity.ok().build();
    }
}

package com.roomManagement.controller;


import com.roomManagement.dto.UserResponse;
import com.roomManagement.service.MailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Tag(name = "MailSenderController")
@RestController
@RequestMapping("roomManagement")
public record MailSenderController(
      //   RestTemplate restTemplate,
        MailService mailService
) {

    @PostMapping("/mailSender/userId/{userId}")
    public ResponseEntity<Void> sendEmail(@PathVariable final Long userId) {
        mailService.sendEmail(userId);

//        final String subject = "Booking confirmation for " + userResponse[0].getName();
//
//        final String body = "Your booking has been confirmed. Enjoy.";
//
//        mailService.sendEmail(userResponse[0].getEmail(), subject, body);

        return ResponseEntity.ok().build();
    }
}

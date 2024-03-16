package com.roomManagement.controller;


import com.roomManagement.service.MailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "MailSenderController")
@RestController
@RequestMapping("roomManagement")
public record MailSenderController(
        MailService mailService
) {

  @PostMapping("/mailSender/userId/{userId}")
  public ResponseEntity<Void> sendEmail(@PathVariable final Long userId) {
    mailService.sendEmail(userId);
    return ResponseEntity.ok().build();
  }
}

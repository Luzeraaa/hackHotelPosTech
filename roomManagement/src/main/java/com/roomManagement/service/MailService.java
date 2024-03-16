package com.roomManagement.service;

import com.roomManagement.dto.user.UserDTO;
import com.roomManagement.exception.NotFoundException;
import com.roomManagement.gateway.UserGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

import static java.lang.String.format;

@Slf4j
@Service
public class MailService {

  private static final String USER_NOT_FOUND = "User not found";
  private static final String EMAIL_TITLE = "reservation completed number: %s";
  private final JavaMailSender javaMailSender;
  private final UserGateway userGateway;


  public MailService(JavaMailSender javaMailSender, UserGateway userGateway) {
    this.javaMailSender = javaMailSender;
    this.userGateway = userGateway;
  }


  public void sendEmail(Long userId) {
    var user = userGateway.getUser(userId)
            .results()
            .stream()
            .findFirst()
            .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));


    sendEmail(user.email(), getEmailTitle(), getBody(user));
  }


  public void sendEmail(final String to, final String subject, final String body) {
    final SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(to);
    mailMessage.setSubject(subject);
    mailMessage.setText(body);

    javaMailSender.send(mailMessage);
  }

  private String getEmailTitle() {
    return format(EMAIL_TITLE, new Random());
  }

  private String getBody(UserDTO userDTO) {
    return String.format("""
            %s %s your booking has been confirmed. Enjoy.
            """, userDTO.name(), userDTO.surname());

  }
}

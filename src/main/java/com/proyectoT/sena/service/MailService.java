package com.proyectoT.sena.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void sendActivationEmail(String to, String key) {
        String link = "http://localhost:8080/api/activate?key=" + key;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Activa tu cuenta");
        message.setText("Haz clic para activar tu cuenta:\n\n" + link);

        mailSender.send(message);
    }

    public void sendResetPasswordEmail(String to, String key) {
        String link = "http://localhost:8080/api/reset-password?key=" + key;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Recuperación de contraseña");
        message.setText("Haz clic para cambiar tu contraseña:\n\n" + link);

        mailSender.send(message);
    }
}

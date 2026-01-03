package io.github.Keverson_Teodoro.notification_service.service;

import io.github.Keverson_Teodoro.notification_service.DTO.PaymentSuccesEventDTO;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender mailSender;

    public void sendEmail (PaymentSuccesEventDTO paymentSuccesEventDTO) throws Exception {

        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(paymentSuccesEventDTO.customerEmail());
            helper.setSubject("Novo pedido");
            helper.setText("seu pedido foi criado com sucesso");

            mailSender.send(message);
        }catch (MailException exception) {
            throw new Exception("Falha tentar enviar o email " + exception.getMessage());
        }
    }
}

package ro.unibuc.fmi.activityrecommender.service;

import lombok.RequiredArgsConstructor;

import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ro.unibuc.fmi.activityrecommender.exceptions.GenericException;
import ro.unibuc.fmi.activityrecommender.model.EmailModel;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void send(final EmailModel email) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setSubject(email.getSubject());
            helper.setTo(email.getReceivers());
            helper.setText(email.getMessage(), true);
            javaMailSender.send(message);

        } catch (final MessagingException | MailSendException mailException) {
            throw new GenericException("Sending email exception. Try again!");
        }
    }


}

package vn.edu.hcmuaf.freshshop.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.freshshop.abstraction.IEmailService;
import vn.edu.hcmuaf.freshshop.model.AppSettings;
import vn.edu.hcmuaf.freshshop.model.User;
import vn.edu.hcmuaf.freshshop.model.VerificationToken;
import vn.edu.hcmuaf.freshshop.repository.VerificationTokenRepository;

import java.io.UnsupportedEncodingException;

@Component
public class EmailService implements IEmailService {
    @Autowired
    private Environment environment;
    @Autowired
    private AppSettings appSettings;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Override
    public void sendMailVerification(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = appSettings.getEmail();
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFirstname() + " " + user.getLastname());
        VerificationToken token = verificationTokenRepository.findByUser(user);
        String verifyURL = siteURL + "verify?code=" + token.getToken();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }


}

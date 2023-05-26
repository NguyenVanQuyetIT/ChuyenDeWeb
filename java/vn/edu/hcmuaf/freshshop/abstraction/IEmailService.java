package vn.edu.hcmuaf.freshshop.abstraction;

import jakarta.mail.MessagingException;
import vn.edu.hcmuaf.freshshop.model.User;

import java.io.UnsupportedEncodingException;

public interface IEmailService {
    void sendMailVerification(User user, String siteURL) throws MessagingException, UnsupportedEncodingException;
}

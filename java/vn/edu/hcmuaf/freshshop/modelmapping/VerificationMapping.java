package vn.edu.hcmuaf.freshshop.modelmapping;



import vn.edu.hcmuaf.freshshop.model.User;
import vn.edu.hcmuaf.freshshop.model.VerificationToken;

import java.util.Date;
import java.util.UUID;


public class VerificationMapping {
    public static VerificationToken mapToVerificationToken(String token, User user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setId(UUID.randomUUID());
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(new Date(new Date().getTime() + 1000 * 60 * 60 * 24));
        return verificationToken;
    }
}

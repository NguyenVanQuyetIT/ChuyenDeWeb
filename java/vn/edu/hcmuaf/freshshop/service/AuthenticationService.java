package vn.edu.hcmuaf.freshshop.service;


import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vn.edu.hcmuaf.freshshop.abstraction.IEmailService;
import vn.edu.hcmuaf.freshshop.configure.JwtService;
import vn.edu.hcmuaf.freshshop.model.*;
import vn.edu.hcmuaf.freshshop.modelmapping.UserMapping;
import vn.edu.hcmuaf.freshshop.modelmapping.VerificationMapping;
import vn.edu.hcmuaf.freshshop.repository.RoleRepository;
import vn.edu.hcmuaf.freshshop.repository.UserRepository;
import vn.edu.hcmuaf.freshshop.repository.UserRoleRepository;
import vn.edu.hcmuaf.freshshop.repository.VerificationTokenRepository;
import vn.edu.hcmuaf.freshshop.request.RegisterRequest;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private final IEmailService sendMailService;

    @Autowired
    private final UserRoleRepository userRoleRepository;

    public void register(RegisterRequest request, String siteUrl) {
        Optional<User> checkUser = Optional.ofNullable(userRepository.findByEmail(request.getEmail()));
        if (checkUser.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        UUID roleId = UUID.fromString("00000000-0000-0000-0000-000000000002");
        Role role = roleRepository.findById(roleId).get();
        User user = UserMapping.mapToUser(request, passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        UserRole userRole = UserMapping.mapToRole(user, role);
        userRoleRepository.save(userRole);
        String randomToken = RandomString.make(64);
        VerificationToken verificationToken = VerificationMapping.mapToVerificationToken(randomToken, user);
        verificationTokenRepository.save(verificationToken);
        try {
            sendMailService.sendMailVerification(user, siteUrl);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verify(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token");
        }
        User user = verificationToken.getUser();
        if (user == null || user.isEnabled())
            return false;
        Date now = new Date();
        if (verificationToken.getExpiryDate().getTime() - now.getTime() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token expired");
        }
        user.setEmailVerified(true);
        userRepository.save(user);
        return true;
    }

    public String authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username or password");
        }
        var user = userRepository.findByEmail(request.getEmail());
        if (!user.isEmailVerified()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not enabled");
        }
//        if (user.isBlock())
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is blocked");
        return jwtService.generateToken((UserDetails) user);
    }
}

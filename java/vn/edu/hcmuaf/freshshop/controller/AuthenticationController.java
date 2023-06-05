package vn.edu.hcmuaf.freshshop.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import vn.edu.hcmuaf.freshshop.model.AuthenticationRequest;
import vn.edu.hcmuaf.freshshop.repository.UserRepository;
import vn.edu.hcmuaf.freshshop.request.RegisterRequest;
import vn.edu.hcmuaf.freshshop.service.AuthenticationService;

import javax.validation.Valid;

@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000/"})
@Controller
public class AuthenticationController {
    @Autowired
    private final AuthenticationService authenticationService;
    @Autowired
    UserRepository userService;
    @Autowired
    private Environment environment;


    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        String result = "User registered successfully";
        try {
            authenticationService.register(request, "http://localhost:3000/");
        } catch (HttpClientErrorException.BadRequest e) {
            result = e.getMessage();
        }
        return result;
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (authenticationService.verify(code)) {
            return "Verification successful";
        } else {
            return "Verification failed";
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody @Valid AuthenticationRequest request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
        try {
            var result = authenticationService.authenticate(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new String());
        }
    }
}

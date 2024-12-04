package com.MakeAPI.jounralAPP.service;

import com.MakeAPI.jounralAPP.entity.LoginUserDto;
import com.MakeAPI.jounralAPP.entity.RegisterUserDto;
import com.MakeAPI.jounralAPP.entity.User;
import com.MakeAPI.jounralAPP.entity.VerifyUserDto;
import com.MakeAPI.jounralAPP.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
@Slf4j
@Service
public class AuthenticationService {
    @Autowired
    private  UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;


    public User signup(User input) {
        String encodedPassword = passwordEncoder.encode(input.getPassword());

        User user = new User(input.getUsername(), input.getEmail(), encodedPassword,input.isSentimentAnalysis());
        user.setVerificationCode(generateVerificationCode());
        user.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(15));
        user.setEnabled(false);
        sendVerificationEmail(user);
        userService.saveNewUser(user);
        return userRepo.save(user);

    }
    public User authenticate(LoginUserDto input) {
        User user = userRepo.findByEmail(input.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isEnabled()) {
            throw new RuntimeException("Account not verified. Please verify your account.");
        }
        try {


            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getEmail(),
                            passwordEncoder.encode(input.getPassword())
                    )
            );
        }catch (Exception e){
            log.info("error",e);
        }

        return user;
    }

    private void sendVerificationEmail(User user) {

            String subject = "Account Verification";
            String verificationCode = user.getVerificationCode();
            String htmlMessage =
                    "<html>"
                            + "<body style=\"font-family: Arial, sans-serif;\">"
                            + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                            + "<h2 style=\"color: #333;\">Welcome to our app!</h2>"
                            + "<p style=\"font-size: 16px;\">Please enter the verification code below to continue:</p>"
                            + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                            + "<h3 style=\"color: #333;\">Verification Code:</h3>"
                            + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"
                            + "</div>"
                            + "</div>"
                            + "</body>"
                            + "</html>";

            try {
                emailService.sendVerificationEmail(user.getEmail(), subject,htmlMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    public static String generateVerificationCode() {
        SecureRandom secureRandom = new SecureRandom();
        final int length = 6;
        int min = (int) Math.pow(10, length - 1);
        int max = (int) Math.pow(10, length) - 1;
        int code = secureRandom.nextInt(max - min + 1) + min;
        return String.valueOf(code);
    }

    public void verifyUser(VerifyUserDto input) {
        Optional<User> optionalUser = userRepo.findByEmail(input.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getVerificationCodeExpiresAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Verification code has expired");
            }
            if (user.getVerificationCode().equals(input.getVerificationCode())) {
                user.setEnabled(true);
                user.setVerificationCode(null);
                user.setVerificationCodeExpiresAt(null);
                userRepo.save(user);
            } else {
                throw new RuntimeException("Invalid verification code");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }
    public void resendVerificationCode(String email) {
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.isEnabled()) {
                throw new RuntimeException("Account is already verified");
            }
            user.setVerificationCode(generateVerificationCode());
            user.setVerificationCodeExpiresAt(LocalDateTime.now().plusHours(1));
            sendVerificationEmail(user);
            userRepo.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }







}

package com.MakeAPI.jounralAPP.controller;

import com.MakeAPI.jounralAPP.Utils.JwtUtil;
import com.MakeAPI.jounralAPP.entity.*;
import com.MakeAPI.jounralAPP.service.AuthenticationService;
import com.MakeAPI.jounralAPP.service.UserDetailsServiceImpl;
import com.MakeAPI.jounralAPP.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
@CrossOrigin
public class PublicController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody User user) {
       User registeredUser = authenticationService.signup(user);
        return ResponseEntity.ok(registeredUser);
    }
    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@RequestBody LoginUserDto loginUserDto) {
        try {
            // Authenticate the user using the provided credentials
            UserDetails authenticatedUser = authenticationService.authenticate(loginUserDto);

            // Generate JWT token for the authenticated user
             String jwtToken = jwtUtil.generateToken(authenticatedUser);// Or use username if needed
            JwtTokenDto jwtTokenDto = new JwtTokenDto();
            jwtTokenDto.setJwtToken(jwtToken);


            // Return the JWT token in the response
            return new ResponseEntity<JwtTokenDto>(jwtTokenDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Return error message if authentication fails
            JwtTokenDto errorResponse = new JwtTokenDto();
            errorResponse.setJwtToken("Error: " + e.getMessage()); // Customizing the error response if needed
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto verifyUserDto) {
        try {
            authenticationService.verifyUser(verifyUserDto);
            return ResponseEntity.ok("Account verified successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/resend")
    public ResponseEntity<?> resendVerificationCode(@RequestParam String email) {
        try {
            authenticationService.resendVerificationCode(email);
            return ResponseEntity.ok("Verification code sent");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/health-check")
    public String heathCheck() {
        return "ok";

    }

}

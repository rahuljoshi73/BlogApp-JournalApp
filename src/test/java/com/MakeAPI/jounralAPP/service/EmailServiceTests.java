package com.MakeAPI.jounralAPP.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;
    @Test
    void testemail(){
        emailService.sendEmail("abc@gmail.com","testing","hello ji");
    }
}

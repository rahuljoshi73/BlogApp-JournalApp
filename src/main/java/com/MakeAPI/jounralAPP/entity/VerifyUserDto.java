package com.MakeAPI.jounralAPP.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class VerifyUserDto {
    private String email;
    private String verificationCode;
}
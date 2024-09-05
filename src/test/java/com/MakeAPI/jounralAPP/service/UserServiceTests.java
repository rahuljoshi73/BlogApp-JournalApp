package com.MakeAPI.jounralAPP.service;

import com.MakeAPI.jounralAPP.entity.User;
import com.MakeAPI.jounralAPP.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTests {
    @Autowired
    UserRepo userRepo;
    @Test
    public void testByUserName() {



        assertEquals(4, 2 + 2);

        assertNotNull(userRepo.findByUserName("ram"));

    }
}

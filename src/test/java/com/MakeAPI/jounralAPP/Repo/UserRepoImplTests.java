package com.MakeAPI.jounralAPP.Repo;

import com.MakeAPI.jounralAPP.repository.UserRepoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepoImplTests {
    @Autowired
    private UserRepoImpl userRepoImpl;
    @Test
    public void tests(){
        Assertions.assertNotNull(userRepoImpl.getUserForSA());

    }
}

package com.MakeAPI.jounralAPP.schedular;

import com.MakeAPI.jounralAPP.scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedularTests {
    @Autowired
    private UserScheduler userScheduler;
    @Test
    public void testFetchUserAndSendMail(){
        userScheduler.fetchUsersAndSendSaMail();
    }
}

package com.MakeAPI.jounralAPP.service;

import com.MakeAPI.jounralAPP.entity.User;
import com.MakeAPI.jounralAPP.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;
    private static final PasswordEncoder pass = new BCryptPasswordEncoder();


    public void saveNewUser(User user) {
        try {
            log.info("user name: {}", user.getUserName());
            user.setPassword(pass.encode(user.getPassword()));
            user.setRoles(List.of("User"));
            userRepo.save(user);
        }catch (Exception e){
            log.warn("this is issue user already exist",e);
        }
    }
    public void saveAdmin(User user) {
        log.info("user name: {}", user.getUserName());
        user.setPassword(pass.encode(user.getPassword()));
        user.setRoles(List.of("ADMIN","User"));
        userRepo.save(user);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public Optional<User> findByID(ObjectId id) {
        return userRepo.findById(id);

    }

    public void deleteBYid(ObjectId id) {
        userRepo.deleteById(id);
    }
   /* public void deleteByUsername(String username){
        userRepo.delete(username);
    }*/

    public User findBYUserName(String userName) {
        return userRepo.findByUserName(userName);
    }


}

package com.MakeAPI.jounralAPP.repository;

import com.MakeAPI.jounralAPP.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User,ObjectId>
{
    static void deleteByUserName(String name) {
    }

    User findByUserName(String username);

    Optional<User> findByEmail(String email);


    User findByVerificationCode(String verificationCode);
}


/*
controller ---> Service ---> repository
 */


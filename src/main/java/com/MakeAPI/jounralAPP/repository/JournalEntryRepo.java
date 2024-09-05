package com.MakeAPI.jounralAPP.repository;

import com.MakeAPI.jounralAPP.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JournalEntryRepo extends MongoRepository<JournalEntry,ObjectId>
{

    Optional<JournalEntry> findById(ObjectId id);
}


/*
controller ---> Service ---> repository
 */


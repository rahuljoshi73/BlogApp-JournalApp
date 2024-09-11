package com.MakeAPI.jounralAPP.service;

import com.MakeAPI.jounralAPP.entity.JournalEntry;
import com.MakeAPI.jounralAPP.entity.User;
import com.MakeAPI.jounralAPP.repository.JournalEntryRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactoryFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserService userService;


    

    @Transactional//only works when exception come
    public void saveEntry(JournalEntry journalEntry, String userName) {
            try{
                User user = userService.findBYUserName(userName);
                journalEntry.setDate(LocalDateTime.now());
                JournalEntry saved = journalEntryRepo.save(journalEntry);
                user.getJournalEntries().add(saved);
                userService.saveUser(user);

            }catch(Exception e){
                log.warn("this is serious",e);

                throw new RuntimeException("error");
                //if you are not sending ant exception then transactional not work
            }

    }
    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepo.save(journalEntry);
    }
    public List<JournalEntry> getAll() {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findByID(ObjectId id){
        return journalEntryRepo.findById(id);

    }
    @Transactional
    public boolean deleteBYid(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findBYUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepo.deleteById(id);
            }
        } catch (Exception e) {
            log.error("Error ",e);
            throw new RuntimeException("An error occurred while deleting the entry.", e);
        }
        return removed;
    }



}

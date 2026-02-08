package com.journalentry.JournalEntry.service;


import com.journalentry.JournalEntry.entity.JournalEntry;
import com.journalentry.JournalEntry.entity.User;
import com.journalentry.JournalEntry.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class JournalEntryService {



    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    public JournalEntry createEntry(JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        return journalEntryRepository.save(journalEntry);
    }


    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public JournalEntry getJournalEntryById(ObjectId id){
        return journalEntryRepository.findById(id).orElse(null);
    }


    @Transactional
    public boolean deleteById(ObjectId id, String username){
        boolean removed = false;
        try {
            User user = userService.findByUsername(username);
             removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);

            }
        }catch (Exception e){
            log.error("Error ",e);
            throw new RuntimeException(e.getMessage());
        }
        return removed;
    }

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){

        try{
            User user = userService.findByUsername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        }catch(Exception e){
            System.err.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

}

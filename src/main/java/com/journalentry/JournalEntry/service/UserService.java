package com.journalentry.JournalEntry.service;


import com.journalentry.JournalEntry.entity.User;
import com.journalentry.JournalEntry.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserService {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean saveNewUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        }catch(Exception e){
            log.error("haahah");
            return false;
        }
    }


    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getJournalEntryById(ObjectId id){
        return userRepository.findById(id).orElse(null);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);

    }

    public User saveUser(User user){
        return userRepository.save(user );
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User saveNewAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));

        return userRepository.save(user);
    }
}

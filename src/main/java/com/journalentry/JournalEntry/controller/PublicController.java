package com.journalentry.JournalEntry.controller;


import com.journalentry.JournalEntry.entity.User;
import com.journalentry.JournalEntry.repository.UserRepository;
import com.journalentry.JournalEntry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public PublicController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }


}

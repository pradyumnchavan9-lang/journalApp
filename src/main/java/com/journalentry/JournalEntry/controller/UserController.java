package com.journalentry.JournalEntry.controller;


import com.journalentry.JournalEntry.api.WeatherResponse;
import com.journalentry.JournalEntry.entity.User;
import com.journalentry.JournalEntry.repository.UserRepository;
import com.journalentry.JournalEntry.service.UserService;
import com.journalentry.JournalEntry.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

        @Autowired
        private UserService userService;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private WeatherService weatherService;


        @PutMapping()
        public ResponseEntity<?> updateUser(@RequestBody User user){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User userInDb = userService.findByUsername(username);
            if(userInDb != null){
                userInDb.setUsername(user.getUsername());
                userInDb.setPassword(user.getPassword());
                userService.saveUser(userInDb);
            }

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


        @DeleteMapping
        public ResponseEntity<?> deleteUserById(){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            userRepository.deleteByUsername(auth.getName());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @GetMapping
        public ResponseEntity<?> greetings(){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            WeatherResponse response = weatherService.getWeather("Mumbai");
            String greeting = "";
            if(response != null){
                greeting = ", Weather Feels Like " + response.getCurrent().getFeelslike();
            }
            return new ResponseEntity<>("Hi " + auth.getName() + greeting, HttpStatus.OK);
        }

}

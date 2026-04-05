package com.journalentry.JournalEntry.cache;


import com.journalentry.JournalEntry.entity.ConfigJournalAppEntity;
import com.journalentry.JournalEntry.repository.ConfigJournalAppRepository;
import com.journalentry.JournalEntry.service.EmailService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

        public enum keys{
            WEATHER_API;
        }

        public Map<String,String> appCache = new HashMap<>();

        @Autowired
        private ConfigJournalAppRepository configJournalAppRepository;

        @Autowired
        private EmailService emailService;

        @PostConstruct
        public void init(){
            emailService.sendEmail(
                    "pradyumnchavan422@gmail.com",
                    "Kaise hai app?",
                    "Ham Badhiya"
            );
            appCache = new HashMap<>();
            List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
            for(ConfigJournalAppEntity c : all){
                appCache.put(c.getKey(),c.getValue());
            }

        }
}

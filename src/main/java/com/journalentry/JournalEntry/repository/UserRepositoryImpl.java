package com.journalentry.JournalEntry.repository;

import com.journalentry.JournalEntry.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

        @Autowired
        private MongoTemplate mongoTemplate;

        public List<User> getUserForSA(){
            Query query = new Query();
            query.addCriteria(Criteria.where("email").exists(true));
            query.addCriteria(Criteria.where("email").ne(null).ne(""));
            query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

            return mongoTemplate.find(query,User.class);
        }
}

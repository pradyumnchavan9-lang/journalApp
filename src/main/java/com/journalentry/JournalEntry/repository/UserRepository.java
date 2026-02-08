package com.journalentry.JournalEntry.repository;

import com.journalentry.JournalEntry.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);

    void deleteByUsername(String name);
}

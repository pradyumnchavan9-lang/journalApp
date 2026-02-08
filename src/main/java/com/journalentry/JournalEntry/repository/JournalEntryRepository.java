package com.journalentry.JournalEntry.repository;


import com.journalentry.JournalEntry.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {


}

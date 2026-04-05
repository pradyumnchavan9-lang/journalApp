package com.journalentry.JournalEntry.repository;

import com.journalentry.JournalEntry.entity.ConfigJournalAppEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository  extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}

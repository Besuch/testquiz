package com.green.testquiz.repository;

import com.green.testquiz.datalayer.entities.Account;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, ObjectId> {
    Account findByEmail(String email);
}

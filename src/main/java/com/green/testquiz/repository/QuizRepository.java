package com.green.testquiz.repository;

import com.green.testquiz.datalayer.entities.Quiz;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends MongoRepository<Quiz, ObjectId> {
}

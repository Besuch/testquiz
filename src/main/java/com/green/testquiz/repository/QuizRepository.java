package com.green.testquiz.repository;

import com.green.testquiz.datalayer.entities.Quiz;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by medvedevyakov on 2019-12-12.
 */
public interface QuizRepository extends MongoRepository<Quiz, ObjectId> {
}

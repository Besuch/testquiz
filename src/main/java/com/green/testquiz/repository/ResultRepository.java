package com.green.testquiz.repository;

import com.green.testquiz.datalayer.entities.Result;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends MongoRepository<Result, ObjectId> {
    List<Result> findByQuizIdAndAccountId(ObjectId quizId, ObjectId accountId);
    List<Result> findByQuizIdAndAccountIdAndQuestionId(ObjectId quizId, ObjectId accountId, ObjectId quiestionId);
}

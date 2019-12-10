package com.green.testquiz.datalayer.entities;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class Result extends Quiz {
    private ObjectId resultId;
    private Double statistics;
    private ObjectId accountId;
    private Integer cursor;
}

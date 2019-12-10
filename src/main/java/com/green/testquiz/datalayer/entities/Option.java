package com.green.testquiz.datalayer.entities;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class Option {
    private ObjectId optionId;
    private String text;
    private boolean isCorrect;
    private boolean isChecked;
}

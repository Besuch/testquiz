package com.green.testquiz.datalayer.entities;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Option {
    @Id
    private ObjectId optionId;
    private String text;
    private boolean isCorrect;
    private boolean isChecked;
}

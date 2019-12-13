package com.green.testquiz.datalayer.entities;

import com.green.testquiz.enums.AccountRole;

import org.bson.types.ObjectId;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Account {
    @Id
    private ObjectId accountId;
    private String firstName;
    private String lastName;
    private String email;
    private AccountRole role;
}
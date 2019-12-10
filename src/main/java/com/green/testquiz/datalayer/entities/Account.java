package com.green.testquiz.datalayer.entities;

import com.green.testquiz.enums.AccountRole;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class Account {
    private ObjectId accountId;
    private String firstName;
    private String lastName;
    private String email;
    private AccountRole role;
}
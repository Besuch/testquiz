package com.green.testquiz.datalayer.mocks;

import com.green.testquiz.datalayer.entities.Account;
import com.green.testquiz.enums.AccountRole;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MockAccountList {
    public static final List<Account> ACCOUNTS;

    static {
        Account user = new Account(new ObjectId(),"User","User","user@gmail.com", AccountRole.USER);

        Account admin = new Account(new ObjectId(),"Admin","Admin","admin@gmail.com", AccountRole.ADMIN);

        ACCOUNTS = Arrays.asList(user, admin);
    }
}

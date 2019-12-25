package com.green.testquiz.exceptions;

import lombok.Getter;

public class EntityNotFoundException extends Exception {

    @Getter
    private final String entityName;

    @Getter
    private final String entityValue;

    private static final String DEFAULT_MESSAGE = "entity does not exist";

    public EntityNotFoundException(String entityName, String entityValue, String message) {
        super(message);
        this.entityName = entityName;
        this.entityValue = entityValue;
    }

    public EntityNotFoundException(String message) {
        this(null, null, message);
    }

    public EntityNotFoundException(String entityName, String entityValue) {
        super(DEFAULT_MESSAGE);
        this.entityName = entityName;
        this.entityValue = entityValue;
    }
}

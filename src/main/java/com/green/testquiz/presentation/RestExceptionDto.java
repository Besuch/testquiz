package com.green.testquiz.presentation;

import lombok.Getter;

public class RestExceptionDto {

    @Getter
    private String message;

    public RestExceptionDto(String message) {
        this.message = message;
    }
}

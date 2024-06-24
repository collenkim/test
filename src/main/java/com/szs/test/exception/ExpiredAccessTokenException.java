package com.szs.test.exception;

public class ExpiredAccessTokenException extends RuntimeException{

    public ExpiredAccessTokenException(String message){
        super(message);
    }

}

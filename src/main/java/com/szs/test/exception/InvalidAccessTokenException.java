package com.szs.test.exception;

public class InvalidAccessTokenException extends RuntimeException{

    public InvalidAccessTokenException(String message){
        super(message);
    }

}

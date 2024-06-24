package com.szs.test.exception;

public class NotExistsDataException extends RuntimeException{

    public NotExistsDataException(String message){
        super(message);
    }
}

package com.szs.test.exception;

public class NotSignupUserException extends RuntimeException{

    public NotSignupUserException(String message){
        super(message);
    }

}

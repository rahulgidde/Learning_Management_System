package com.bridgelabz.lmsapplication.exception;

public class UserException extends RuntimeException {
    public exceptionType type;

    public UserException(exceptionType type, String message) {
        super(message);
        this.type = type;
    }

    public enum exceptionType {
        User_Not_FOUND, INVALID_EMAIL_ID, FiLE_NOT_FOUND, INVALID_TOKEN;
    }
}

package com.example.NewsFeed.exception;

public class NotLoginUserException extends RuntimeException {
    public NotLoginUserException(String message) {
        super(message);
    }
}

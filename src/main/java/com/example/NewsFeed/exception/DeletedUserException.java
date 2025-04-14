package com.example.NewsFeed.exception;

public class DeletedUserException extends RuntimeException{
    public DeletedUserException(String message) {
        super(message);
    }
}

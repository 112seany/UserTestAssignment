package com.example.userscrud.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super(String.format("User with id %s was not found", id));
    }
}

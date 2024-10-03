package com.online.shop.exceptions;

public class InvalidEntityDataException extends RuntimeException {
    public InvalidEntityDataException(String message) {
        super(message);
    }
}
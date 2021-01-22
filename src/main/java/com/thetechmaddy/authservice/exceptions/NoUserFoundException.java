package com.thetechmaddy.authservice.exceptions;

public class NoUserFoundException extends RuntimeException {

    public NoUserFoundException(String message) {
        super(message);
    }

}

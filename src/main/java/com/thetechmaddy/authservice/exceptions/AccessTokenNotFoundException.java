package com.thetechmaddy.authservice.exceptions;

public class AccessTokenNotFoundException extends RuntimeException {

    public AccessTokenNotFoundException(String message) {
        super(message);
    }
}

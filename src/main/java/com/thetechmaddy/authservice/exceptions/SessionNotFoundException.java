package com.thetechmaddy.authservice.exceptions;

public class SessionNotFoundException extends RuntimeException {

    public SessionNotFoundException(String message) {
        super(message);
    }

}

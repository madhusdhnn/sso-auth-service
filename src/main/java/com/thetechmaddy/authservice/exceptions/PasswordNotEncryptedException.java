package com.thetechmaddy.authservice.exceptions;

public class PasswordNotEncryptedException extends RuntimeException {

    public PasswordNotEncryptedException(String message) {
        super(message);
    }

}

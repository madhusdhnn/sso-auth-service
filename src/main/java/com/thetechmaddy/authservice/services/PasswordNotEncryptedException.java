package com.thetechmaddy.authservice.services;

public class PasswordNotEncryptedException extends RuntimeException {

    public PasswordNotEncryptedException(String message) {
        super(message);
    }

}

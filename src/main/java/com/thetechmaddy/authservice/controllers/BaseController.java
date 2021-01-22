package com.thetechmaddy.authservice.controllers;

import com.thetechmaddy.authservice.exceptions.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class BaseController {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public String runtimeException(RuntimeException ex) {
        String message = "Error while processing request";
        log.error(message, ex);
        return message;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String badRequest(BadRequestException ex) {
        String message = "Bad request";
        log.error(message, ex);
        return message;
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String forbidden(ForbiddenException ex) {
        String message = "Forbidden from performing the requested action";
        log.error(message, ex);
        return message;
    }

    @ExceptionHandler(AccessTokenNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String accessTokenNotFound(AccessTokenNotFoundException ex) {
        String message = "Access token not provided";
        log.error(message, ex);
        return message;
    }

    @ExceptionHandler(SessionNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String sessionNotFound(SessionNotFoundException ex) {
        String message = "Session not available, should re-authenticate";
        log.error(message, ex);
        return message;
    }

    @ExceptionHandler(NoUserFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String noUserFoundException(NoUserFoundException ex) {
        String message = "Identities not provided";
        log.info(message, ex);
        return message;
    }

}

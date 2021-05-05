package com.yousry.bookstore.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UserUnauthorizedException extends ServletException {

    private static final long serialVersionUID = 2L;

    public UserUnauthorizedException(String message) {
        super(message);
    }
}

package com.yousry.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Not a valid Request.")
public class FullFaultRequestException extends Exception {
    static final long serialVersionUID = -3387516993224229948L;


    public FullFaultRequestException(String message) {
        super(message);
    }
}

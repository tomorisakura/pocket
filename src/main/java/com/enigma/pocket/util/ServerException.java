package com.enigma.pocket.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServerException extends RuntimeException {

    public ServerException(String message) {
        super(message);
    }
}

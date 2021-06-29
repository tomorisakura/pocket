package com.enigma.pocket.util;

import java.time.LocalDateTime;

public class ResponseMessage<T> {
    private Integer statusCode;
    private String message;
    private LocalDateTime timeStamp = LocalDateTime.now();
    private T data;

    public ResponseMessage(Integer statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public static <T>ResponseMessage<T> success(Integer code, T data) {
        return new ResponseMessage<>(code, "Success", data);
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

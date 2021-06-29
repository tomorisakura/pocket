package com.enigma.pocket.util;

public class Response<T> {
    private Integer code;
    private Boolean status;
    private T data;

    public Response(Integer code, Boolean status, T data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

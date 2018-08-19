package com.itxiaoer.dimg.plugin;

import lombok.Data;

/**
 * @author : liuyk
 */
@Data
public class Response<T> {
    private boolean success;
    private T data;
    private String message;

    @SuppressWarnings("unused")
    private Response() {

    }

    private Response(boolean success) {
        this.success = success;
    }

    private Response(T data) {
        this.data = data;
        this.success = true;
    }

    private Response(boolean success, String message) {
        this.message = message;
        this.success = success;
    }


    public static <T> Response<T> ok() {
        return new Response<>(true);
    }


    public static <T> Response<T> ok(T data) {
        return new Response<>(data);
    }

    public static <T> Response<T> error(String message) {
        return new Response<>(false, message);
    }

}

package com.example.redundantcode.api.response;

import lombok.Data;

/**
 * https://time.geekbang.org/column/article/228968
 * @param <T>
 */

@Data
public class APIResponse<T> {
    private boolean success;
    private T data;
    private int code;
    private String message;
}

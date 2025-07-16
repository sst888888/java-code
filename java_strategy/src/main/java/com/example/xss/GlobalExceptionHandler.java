package com.example.xss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public String handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        return e.getAllErrors().get(0).getDefaultMessage();
    }


    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return e.getBindingResult().getFieldError().getDefaultMessage();
    }


    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public  String handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return "服务异常，请稍后重试！";
    }

}

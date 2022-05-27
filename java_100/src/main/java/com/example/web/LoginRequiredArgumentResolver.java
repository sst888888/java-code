package com.example.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Slf4j
public class LoginRequiredArgumentResolver implements HandlerMethodArgumentResolver {
    //解析哪些参数
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //匹配参数上具有@LoginRequired注解的参数
        return methodParameter.hasParameterAnnotation(LoginRequired.class);
    }


    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        LoginRequired loginRequired = methodParameter.getParameterAnnotation(LoginRequired.class);

        if(null == loginRequired) {
            return null;
        }

        Object object = nativeWebRequest.getAttribute(loginRequired.sessionKey(), RequestAttributes.SCOPE_SESSION);
        if (object == null) {
            log.error("接口 {} 非法调用！", Objects.requireNonNull(methodParameter.getMethod()));
            throw new RuntimeException("请先登录！");
        }
        return object;
    }
}

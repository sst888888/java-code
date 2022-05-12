package com.common.interceptor;

import com.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName AuthorityInterceptor
 * @Description
 * @Author Chaiphat
 * @Date 2020/10/23 22:45
 * @Version 1.0
 **/
@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String name = handlerMethod.getMethod().getName();
        // 得到Controller名
        String className = handlerMethod.getBean().getClass().getSimpleName();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Iterator iterator = parameterMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            String key = (String) entry.getKey();
        }

        if(StringUtils.pathEquals(className,"aa")) {
            return true;
        }

        // 以下必须设置
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        // 获取输出对象
        PrintWriter out = response.getWriter();
        out.print(JsonUtil.obj2String("哈哈"));
        out.flush();
        out.close();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("afterCompletion");
    }
}

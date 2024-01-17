package org.jdbc.demo02;

import javafx.print.Collation;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

@Intercepts({
        @Signature(type= ResultSetHandler.class,method = "handleResultSets",args = {Statement.class})})
public class ResultSetPlugin implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(ResultSetPlugin.class);

    /**
     * 带注解的字段脱敏
     * @param object
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private Object desensitize(Object object) throws InvocationTargetException, IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field: fields) {
            Confidential confidential = field.getAnnotation(Confidential.class);
            if (confidential == null) {
                continue;
            }

            PropertyDescriptor ps = BeanUtils.getPropertyDescriptor(object.getClass(), field.getName());
            if (ps.getReadMethod() == null || ps.getWriteMethod() == null) {
                continue;
            }

            Object value = ps.getReadMethod().invoke(object);
            if (value != null) {
                ps.getWriteMethod().invoke(object, "***");
            }
        }

        return object;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("Result Plugin 拦截 :"+invocation.getMethod());
        Object result = invocation.proceed();
        if (result instanceof Collation) {
            Collection<Object> objList = (Collection) result;
            List<Object> resultList = new ArrayList<>();
            for (Object obj: objList) {
                resultList.add(desensitize(obj));
            }
            return resultList;
        } else {
            return desensitize(result);
        }
    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}
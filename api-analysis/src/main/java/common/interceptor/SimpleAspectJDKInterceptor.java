package common.interceptor;

import common.aspect.Aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @ClassName SimpleAspectJDKInterceptor
 * @Description 
 * @Author Chaiphat
 * @Date 2020/6/27 16:47
 * @Version 1.0
 **/
public class SimpleAspectJDKInterceptor implements InvocationHandler {
    private Object target;//目标代理对象
    private Aspect aspect;
    private Class classz;

    public SimpleAspectJDKInterceptor() {
    }

    public SimpleAspectJDKInterceptor(Object target, Aspect aspect, Class classz) {
        this.target = target;
        this.aspect = aspect;
        this.classz = classz;
    }

    public Object getTarget() {
        return target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final Object target = this.target;
        final Aspect aspect = this.aspect;
        aspect.before(target, method, args);
        Object ret = null;
        try {
            ret = method.invoke(Modifier.isStatic(method.getModifiers()) ? null : target, args);
        } catch (Exception e) {
            aspect.afterException(target, method, args);
            return null;
        }
        aspect.after(target, method, args);
        return ret;
    }
}

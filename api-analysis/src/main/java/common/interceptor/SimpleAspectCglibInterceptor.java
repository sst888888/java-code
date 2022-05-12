package common.interceptor;

import common.aspect.Aspect;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName SimpleAspectCglibInterceptor
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/27 16:47
 * @Version 1.0
 **/
public class SimpleAspectCglibInterceptor implements MethodInterceptor {
    private Object target;//目标代理对象
    private Aspect aspect;
    private Class classz;

    public SimpleAspectCglibInterceptor(Object target, Class classz, Aspect aspect) {
        this.target = target;
        this.classz = classz;
        this.aspect = aspect;
    }

    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(classz);
        //通过回调指定代理类。
        enhancer.setCallback(SimpleAspectCglibInterceptor.this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        aspect.before(o, method, objects);
        Object ret = null;
        try {
            ret = method.invoke(target, objects);
        } catch (Exception e) {
            aspect.afterException(o, method, objects);
            return null;
        }
        aspect.after(o, method, objects);
        return ret;
    }
}

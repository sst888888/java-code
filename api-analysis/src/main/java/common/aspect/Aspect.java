package common.aspect;

import java.lang.reflect.Method;

public interface Aspect {

    void before(Object target, Method method, Object[] args);

    void after(Object target, Method method, Object[] args);

    void afterException(Object target, Method method, Object[] args);

}

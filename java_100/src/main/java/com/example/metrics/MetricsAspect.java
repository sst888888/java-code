package com.example.metrics;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@Aspect
@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MetricsAspect {

    // 实现一个返回Java基本类型默认值的工具。
    // 其实，你也可以逐一写很多if-else判断类型，然后手动设置其默认值。
    // 这里为了减少代码量用了一个小技巧，即通过初始化一个具有1个元素的数组，然后通过获取这个数组的值来获取基本类型默认值。
    private static final Map<Class<?>, Object> DEFAULT_VALUES = Stream
            .of(boolean.class, byte.class, char.class, double.class, float.class, int.class, long.class, short.class)
            .collect(toMap(clazz -> (Class<?>) clazz, clazz -> Array.get(Array.newInstance(clazz, 1), 0)));

    // 让Spring帮我们注入ObjectMapper，以方便通过JSON序列化来记录方法入参和出参
    @Autowired
    private ObjectMapper objectMapper;

    public static <T> T getDefaultValue(Class<T> clazz) {
        return (T) DEFAULT_VALUES.get(clazz);
    }

//    @Pointcut("@annotation(org.geekbang.time.commonmistakes.spring.demo2.Metrics)")
//    public void withMetricsAnnotation() {
//    }

    // @annotation指示器实现对标记了Metrics注解的方法进行匹配
    @Pointcut("within(@com.example.springbootdemo.springpart1.aopmetrics.Metrics *)")
    public void withMetricsAnnotation() {
    }

    // within指示器实现了匹配那些类型上标记了@RestController注解的方法
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerBean() {
    }

    @Around("controllerBean() || withMetricsAnnotation())")
    public Object metrics(ProceedingJoinPoint pjp) throws Throwable {
        // 通过连接点获取方法签名和方法上Metrics注解，并根据方法签名生成日志中要输出的方法定义描述。
        // 尝试获取当前方法的类名和方法名
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String name = String.format("【%s】【%s】", signature.getDeclaringType().toString(), signature.toLongString());

        // 先从方法上获取注解
        Metrics metrics = signature.getMethod().getAnnotation(Metrics.class);
        // 因为需要默认对所有@RestController标记的Web控制器实现@Metrics注解的功能，
        // 在这种情况下方法上必然是没有@Metrics注解的，我们需要获取一个默认注解。
        // 虽然可以手动实例化一个@Metrics注解的实例出来，但为了节省代码行数，
        // 我们通过在一个内部类上定义@Metrics注解方式，然后通过反射获取注解的小技巧，来获得一个默认的@Metrics注解的实例
        if (metrics == null) {
            // 方法上获取不到注解，再从类上获取注解。
            metrics = signature.getMethod().getDeclaringClass().getAnnotation(Metrics.class);
        }
        // 对于Controller和Repository，我们需要初始化一个@Metrics注解出来
        // 方法和类上都获取不到注解，使用默认的注解。
        if (metrics == null) {
            @Metrics
            final class c {
            }
            metrics = c.class.getAnnotation(Metrics.class);
        }
        // 对于Web项目我们可以从上下文中获取到额外的一些信息来丰富我们的日志
        // 尝试从请求上下文（如果有的话）获得请求URL，以方便定位问题
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            if (request != null) {
                name += String.format("【%s】", request.getRequestURL().toString());
            }
        }
        // 实现的是入参的日志输出
        if (metrics.logParameters()){
            log.info(String.format("【入参日志】调用 %s 的参数是：【%s】", name, objectMapper.writeValueAsString(pjp.getArgs())));
        }
        // 实现连接点方法的执行，以及成功失败的打点，出现异常的时候还会记录日志
        // 这里我们通过日志方式暂时替代了打点的实现，标准的实现是需要把信息对接打点服务，比如Micrometer
        Object returnValue;
        Instant start = Instant.now();
        try {
            returnValue = pjp.proceed();
            if (metrics.recordSuccessMetrics()) {
                log.info(String.format("【成功打点】调用 %s 成功，耗时：%d ms", name, Duration.between(start, Instant.now()).toMillis()));
            }
        } catch (Exception ex) {
            if (metrics.recordFailMetrics()) {
                log.info(String.format("【失败打点】调用 %s 失败，耗时：%d ms", name, Duration.between(start, Instant.now()).toMillis()));
            }
            if (metrics.logException()) {
                log.error(String.format("【异常日志】调用 %s 出现异常！", name), ex);
            }
            // 如果忽略异常那么直接返回默认值
            // 忽略异常的时候，使用一开始定义的getDefaultValue方法，来获取基本类型的默认值
            if (metrics.ignoreException()) {
                returnValue = getDefaultValue(signature.getReturnType());
            }
            else {
                throw ex;
            }
        }
        // 实现了返回值的日志输出
        if (metrics.logReturn()) {
            log.info(String.format("【出参日志】调用 %s 的返回是：【%s】", name, returnValue));
        }
        return returnValue;
    }
}
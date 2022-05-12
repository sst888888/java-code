package common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Component
public @interface ApiMonitor {
    String apiName() default "";
    String time() default "";

}

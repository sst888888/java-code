package common.util;

import java.lang.annotation.ElementType;
import java.lang.reflect.GenericDeclaration;
import java.util.HashSet;
import java.util.Set;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import common.annotation.ApiMonitor;
import common.annotation.Service;
/**
 * @ClassName BeanUtils
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/27 15:58
 * @Version 1.0
 **/
public class BeanUtils {

    public static Set<? extends GenericDeclaration> scanAnnotationByPackageName(String packageName, ElementType type) {
        if (packageName == null || packageName.isEmpty()) {
            throw new Error("扫描的包名为空！");
        }
        if (type == null) {
            throw new Error("扫描的注解类型为空！");
        }
        if (type == ElementType.TYPE) {
            //用于类级别的注解
            return new Reflections(packageName).getTypesAnnotatedWith(Service.class);
        }
        if (type == ElementType.METHOD) {
            return new Reflections(packageName, new MethodAnnotationsScanner())
                    .getMethodsAnnotatedWith(ApiMonitor.class);
        }
        return new HashSet<>();
    }

}

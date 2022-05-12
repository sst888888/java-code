package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @ClassName MyBeanUtils
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/24 21:38
 * @Version 1.0
 **/
public class MyBeanUtils {

    public static Object populate(Class cls, Map<String, Object> map) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object obj = null;
        obj = cls.newInstance();

        Field[] fields = cls.getDeclaredFields();
        for(Field fld : fields) {
            String fldName = fld.getName();
            Object value = map.get(fld.getName());
            if(value == null) {
                System.out.println(fld.getName() + "的数据为空");
            } else {
                // 如果map中存在对应的属性数据，则由属性名得出它的setter方法的名字 
                String methodName = "set" + fldName.substring(0, 1).toUpperCase() + fldName.substring(1);
                // 根据方法名和参数的数据类型(其实就是属性的类型)，获得Method对象
                Method method=cls.getDeclaredMethod(methodName, fld.getType());
                // 调用该method对象所代表的方法
                Object[] params = new Object[1];
                params[0] = value;
                method.invoke(obj,params);
            }
        }

        return obj;
    }


    public static<T>  T populate2(Class<T> cls,Map<String, Object> map) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        T obj = cls.newInstance();
        Field[] fields = cls.getDeclaredFields();

        for(Field fld : fields) {
            String fldName = fld.getName();
            Object value = map.get(fld.getName());
            if(value == null) {
                System.out.println(fld.getName() + "的数据为空");
            } else {
                // 如果map中存在对应的属性数据，则由属性名得出它的setter方法的名字
                String methodName = "set" + fldName.substring(0, 1).toUpperCase() + fldName.substring(1);
                // 根据方法名和参数的数据类型(其实就是属性的类型)，获得Method对象
                Method method=cls.getDeclaredMethod(methodName, fld.getType());
                // 调用该method对象所代表的方法
                Object[] params = new Object[1];
                params[0] = value;
                method.invoke(obj,params);
            }
        }

        return obj;
    }
}

package reflect;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @ClassName BeanInfoUtil
 * @Description   前者通过创建对象直接获得，后者需要遍历，所以使用PropertyDescriptor类更加方便。
 * @Author Chaiphat
 * @Date 2020/7/24 21:59
 * @Version 1.0
 **/
public class BeanInfoUtil {

    /**
     * 设置bean的某个属性值
     * @param userInfo
     * @param userName
     */
    public static void setProperty(UserInfo userInfo, String userName) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        // 获取bean的某个属性的描述符
        PropertyDescriptor propDesc = new PropertyDescriptor(userName, UserInfo.class);
        // 获得用于写入属性值的方法
        Method writeMethod = propDesc.getWriteMethod();
        // 写入属性值
        writeMethod.invoke(userInfo,"wrong");
        System.out.println("set userName: " + userInfo.getUserName());
    }

    /**
     * 获取bean的某个属性值
     * @param userInfo
     * @param userName
     */
    public static void getProperty(UserInfo userInfo, String userName) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        // 获得bean的某个属性的描述符
        PropertyDescriptor propDesc = new PropertyDescriptor(userName, UserInfo.class);
        // 获得用于读取属性值的方法
        Method readMethod = propDesc.getReadMethod();
        // 读取属性值
        Object objUserName = readMethod.invoke(userInfo);
        System.out.println("get userName: " + objUserName.toString());
    }


    /**
     * 通过内省设置bean的某个属性值
     * @param userInfo
     * @param userName
     */
    public static void setPropertyByIntrospector(UserInfo userInfo, String userName) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        // 获取bean信息
        BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo.class);
        // 获取bean的所有属性列表
        PropertyDescriptor[] proDescriptors = beanInfo.getPropertyDescriptors();
        if(proDescriptors != null && proDescriptors.length > 0) {
            for(PropertyDescriptor proDesc : proDescriptors) {
                // 找到则写入属性值
                if(proDesc.getName().equals(userName)) {
                    Method writeMethod = proDesc.getWriteMethod();
                    // 写入属性值
                    writeMethod.invoke(userInfo, "alan");
                    System.out.println("set userName: " + userInfo.getUserName());
                    break;
                }
            }
        }
    }

    /**
     * 通过内省获取bean的某个属性值
     * @param userInfo
     * @param userName
     */
    public static void getPropertyByIntrospector(UserInfo userInfo, String userName) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        if(propertyDescriptors != null && propertyDescriptors.length > 0) {
            for(PropertyDescriptor pro : propertyDescriptors) {
                if(pro.getName().equals(userName)) {
                    Method readMethod = pro.getReadMethod();
                    Object invoke = readMethod.invoke(userInfo);
                    System.out.println("get userName: " + invoke.toString());
                    break;
                }
            }
        }
    }

    private static void printObject(Object obj) {
        Class<?> clazz = obj.getClass();
        if(clazz.isArray()) {
            // 如果是数组,利用数组的反射类Array类来进行相关操作。
            int len = Array.getLength(obj);
            for(int i = 0; i < len; i++) {
                System.out.println(Array.get(obj, i));
            }
        } else {
            System.out.println(obj);
        }
    }

    public static void main(String[] args) {
        printObject("ssss");
        printObject(new int[]{1,2,3});
    }
}

package reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Demo
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/24 20:11
 * @Version 1.0
 **/
public class Demo {

    @Test
    public void test1() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName("reflect.Person");
        // 1、可以通过该Class对象的newInstance()方法直接生成
        Person pers = (Person)clazz.newInstance();

        // 2、通过构造函数的方式反射出对象
        Constructor<?> constructor = clazz.getConstructor(null);
        Person person = (Person)constructor.newInstance(null);
        System.out.println(person.name);
    }

    @Test
    public void test2() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName("reflect.Person");
        Constructor<?> constructor = clazz.getConstructor(String.class);
        Person person = (Person)constructor.newInstance("hello");
        System.out.println(person.name);
    }

    @Test
    public void test03() throws Exception {
        Class<?> clazz = Class.forName("reflect.Person");
        Constructor constructor = clazz.getConstructor(String.class,int.class);
        Person person  = (Person) constructor.newInstance("hello",123);
        System.out.println(person.name);
    }

    @Test
    public void test04() throws Exception {
        Class<?> clazz = Class.forName("reflect.Person");
        Constructor constructor = clazz.getDeclaredConstructor(List.class);
        constructor.setAccessible(true);
        Person person  = (Person) constructor.newInstance(new ArrayList());
        System.out.println(person.name);
    }


}

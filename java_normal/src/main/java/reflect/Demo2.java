package reflect;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName Demo2
 * @Description 解析类 并调用方法
 * @Author Chaiphat
 * @Date 2020/7/24 20:45
 * @Version 1.0
 **/
public class Demo2 {

    @Test
    public void test1() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> aClass = Class.forName("reflect.Person");
        Person person = (Person)aClass.newInstance();
        Method method = aClass.getMethod("sayHello", null);
        method.invoke(person,null);
    }


    @Test
    public void test02() throws Exception {
        Class<?> clazz = Class.forName("reflect.Person");
        Person person = (Person) clazz.newInstance();
        Method method = clazz.getMethod("sayHello", String.class);
        method.invoke(person, "world");
    }

    @Test
    public void test03() throws Exception {
        Class<?> clazz = Class.forName("reflect.Person");
        Person person = (Person) clazz.newInstance();
        Method method = clazz.getMethod("sayHello", String.class, int.class);
        method.invoke(person, "world", 12);
    }

    @Test
    public void test04() throws Exception {
        Class<?> clazz = Class.forName("reflect.Person");
        Person person = (Person) clazz.newInstance();
        Method method = clazz.getMethod("sayHello",Person.class);
        method.invoke(null,person);
    }


    @Test
    public void test05() throws Exception {
        Class<?> clazz = Class.forName("reflect.Person");
        Person person = (Person) clazz.newInstance();
        Method method = clazz.getDeclaredMethod("sayHello", InputStream.class);
        method.setAccessible(true);
        method.invoke(person,new FileInputStream("D:\\t.txt"));
    }


    @Test
    public void test06() throws Exception {
        Class<?> clazz = Class.forName("reflect.Person");
        //Person person = (Person) clazz.newInstance();
        Method method = clazz.getMethod("main",String[].class);
        // 静态方法 所以可以直接传空
        method.invoke(null,new Object[]{new String[]{"1","2"}});
    }

}

package reflect;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @ClassName Demo3
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/24 21:18
 * @Version 1.0
 **/
public class Demo3 {

    @Test
    public void test01() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> aClass = Class.forName("reflect.Person");
        Person person = (Person)aClass.newInstance();
        Field field = aClass.getField("name");
        Object object = field.get(person);
        if(field.getType().equals(String.class)) {
            System.out.println((String)object);
        }
    }

    @Test
    public void test02() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> aClass = Class.forName("reflect.Person");
        Person person = (Person)aClass.newInstance();
        Field field = aClass.getDeclaredField("age");
        field.setAccessible(true);
        Object object = field.get(person);
        System.out.println(object);
    }


    @Test
    public void test03() throws Exception {
        Class<?> clazz = Class.forName("reflect.Person");
        //Person person = (Person) clazz.newInstance();
        Field field = clazz.getField("password");
        Object object = field.get(null);
        System.out.println(object);
    }

}

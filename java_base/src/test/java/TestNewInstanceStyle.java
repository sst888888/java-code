import sun.reflect.ReflectionFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * @ClassName TestNewInstanceStyle
 * @Author Chaiphat
 * @Date 2021/5/10 15:26
 * @Version 1.0
 * @see "https://time.geekbang.org/column/article/371184"
 **/
public class TestNewInstanceStyle {

    public static class TestObject {
        public String name = "fujian";
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ReflectionFactory reflectionFactory = ReflectionFactory.getReflectionFactory();
        Constructor constructor = reflectionFactory.newConstructorForSerialization(TestObject.class, Object.class.getDeclaredConstructor());
        constructor.setAccessible(true);
        TestObject testObject1 = (TestObject) constructor.newInstance();
        System.out.println(testObject1.name);
        //普通方式
        TestObject testObject2 = new TestObject();
        System.out.println(testObject2.name);

        Object data[] = {1, 2, 4, 545, 11, 32, 13131, 4444};
        String s = Arrays.deepToString(data);
        System.out.println(s);

        System.out.println(1 << 30);
    }
}

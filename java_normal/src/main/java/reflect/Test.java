package reflect;

/**
 * @ClassName Test
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/25 17:21
 * @Version 1.0
 **/
public class Test {
    private static Integer count;
    private static int count2;

    public static void main(String[] args) {
        // Example 1: == comparison pure primitive – no autoboxing
        int i1 = 1;
        int i2 = 1;
        System.out.println("i1==i2 : " + (i1 == i2)); // true

        // Example 2: equality operator mixing object and primitive
        Integer num1 = 1; // autoboxing
        int num2 = 1;
        System.out.println("num1 == num2 : " + (num1 == num2)); // true

        // Example 3: special case - arises due to autoboxing in Java
        Integer obj1 = 1; // autoboxing will call Integer.valueOf()
        Integer obj2 = 1; // same call to Integer.valueOf() will return same
        // cached Object

        System.out.println("obj1 == obj2 : " + (obj1 == obj2)); // true

        // Example 4: equality operator - pure object comparison
        Integer one = new Integer(1); // no autoboxing
        Integer anotherOne = new Integer(1);
        System.out.println("one == anotherOne : " + (one == anotherOne)); // false

        if(count2 <= 0) {
            System.out.println("--------");
        }

        if(count <= 0) {
            System.out.println("hhhh");
        }

    }
}

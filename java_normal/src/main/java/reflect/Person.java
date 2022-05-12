package reflect;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName Person
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/24 20:06
 * @Version 1.0
 **/
public class Person {

    public String name = "test";

    private int age = 1;

    public static int password = 12345;

    public Person() {
    }

    public Person(String name) {
        System.out.println("name: " + name);
    }

    public Person(String name,int age) {
        System.out.println("name: " + name + ",age: " + age);
    }

    private Person(List list) {
        System.out.println("collection");
    }

    public void sayHello() {
        System.out.println("hello");
    }

    public void sayHello(String name) {
        System.out.println("hello," + name);
    }

    public String sayHello(String name,int age) {
        System.out.println("hello," + name + ",age:" + age);
        return name;
    }

    private void sayHello(InputStream in) {
        System.out.println("inputStream");
        System.out.println(in);
    }

    public static void sayHello(Person person) {
        System.out.println(person);
    }

    public static void main(String[] args) {
        System.out.println("Person class main method");
    }
}

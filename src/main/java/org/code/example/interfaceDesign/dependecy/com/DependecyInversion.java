package org.code.example.interfaceDesign.dependecy.com;

/**
 * @ClassName DependecyInversion
 * @Description 没有使用依赖倒转原则
 * @Author Chaiphat
 * @Date 2020/6/15 15:34
 * @Version 1.0
 **/
public class DependecyInversion {

    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());
    }
}

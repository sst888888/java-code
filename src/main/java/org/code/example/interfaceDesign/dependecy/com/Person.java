package org.code.example.interfaceDesign.dependecy.com;

/**
 * @ClassName Person
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/15 15:32
 * @Version 1.0
 **/
public class Person {
    public void receive(Email email){
        System.out.println(email.getInfo());
    }
}

package org.code.example.designpatterns.businesspattern;

/**
 * @ClassName Main
 * @Description 测试类
 * @Author Chaiphat
 * @Date 2020/1/9 16:50
 * @Version 1.0
 **/
public class Main {

    public static void main(String[] args) {
        BusinessDelegate businessDelegate = new BusinessDelegate();
        businessDelegate.setServiceType("doCreateCount");

        NormalCustomer client = new NormalCustomer(businessDelegate);
        client.doTask();

        businessDelegate.setServiceType("doSaveMoney");
        client.doTask();

    }

}

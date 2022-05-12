package org.code.example.designpatterns.businesspattern;

/**
 * @ClassName NormalCustomer
 * @Description 普通客户
 * @Author Chaiphat
 * @Date 2020/1/9 16:47
 * @Version 1.0
 **/
public class NormalCustomer {

    /**
     * 要提供服务的业务代表
     */
    private BusinessDelegate businessDelegate;

    public NormalCustomer(BusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    /**
     * 客户端的业务
     */
    public void doTask(){
        // 委托业务代表处理
        businessDelegate.doTask();
    }

}

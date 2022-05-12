package org.code.example.designpatterns.businesspattern;

/**
 * 抽象业务服务
 */
public interface BusinessService {

    /**
     * 要进行的业务  开户
     */
    void doCreateCount();

    /**
     * 要进行的业务  存钱
     */
    void doSaveMoney();
}

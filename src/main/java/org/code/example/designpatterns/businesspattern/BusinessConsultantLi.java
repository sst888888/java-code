package org.code.example.designpatterns.businesspattern;

/**
 * @ClassName BusinessConsultantLi
 * @Description 服务窗口 李
 * @Author Chaiphat
 * @Date 2020/1/9 16:33
 * @Version 1.0
 **/
public class BusinessConsultantLi implements BusinessService {
    @Override
    public void doCreateCount() {
        System.out.println("服务窗口-李：开户成功");
    }

    @Override
    public void doSaveMoney() {
        System.out.println("服务窗口-李：存钱成功");
    }
}

package org.code.example.designpatterns.businesspattern;

/**
 * @ClassName BusinessConsultantZh
 * @Description 服务窗口 张
 * @Author Chaiphat
 * @Date 2020/1/9 16:33
 * @Version 1.0
 **/
public class BusinessConsultantZh implements BusinessService {
    @Override
    public void doCreateCount() {
        System.out.println("服务窗口-张：开户成功");
    }

    @Override
    public void doSaveMoney() {
        System.out.println("服务窗口-张：存钱成功");
    }
}

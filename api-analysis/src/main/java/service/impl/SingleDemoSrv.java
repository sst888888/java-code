package service.impl;

import common.annotation.ApiMonitor;
import common.annotation.Service;
import service.ISingleDemoSrv;

/**
 * @ClassName SingleDemoSrv
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/27 17:02
 * @Version 1.0
 **/
@Service("88")
public class SingleDemoSrv implements ISingleDemoSrv {
    @Override
    @ApiMonitor
    public void doSomething() {
        try {
            System.out.println("doSomething执行中。。。");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package org.code.example.thread.JUC.synchronize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName PrdService
 * @Description
 * @Author Chaiphat
 * @Date 2020/4/29 15:19
 * @Version 1.0
 **/
public class PrdService {
    private static final Logger logger = LoggerFactory.getLogger(PrdService.class);
    private String name;
    private String pwd;
    String lock = new String();

    public void setUsernamePassword(String username,String password){
        synchronized (lock){
            logger.info("PrdService thread name = {}, 进入代码快：{}", Thread.currentThread().getName() ,System.currentTimeMillis());
            name = username;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            pwd = password;
            System.out.println("PrdService thread name = "+Thread.currentThread().getName()
                    +" 进入代码快:"+System.currentTimeMillis() + "入参 name = " + name + " 入参 pwd = " + pwd);
        }
    }
}

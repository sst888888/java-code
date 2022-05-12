package org.code.example.thread.JUC.synchronize;

/**
 * @ClassName PrdService
 * @Description
 * @Author Chaiphat
 * @Date 2020/4/29 15:19
 * @Version 1.0
 **/
public class PrdService {

    private String name;
    private String pwd;
    String lock = new String();

    public void setUsernamePassword(String username,String password){
        synchronized (lock){
            System.out.println("PrdService thread name = " + Thread.currentThread().getName()
                    + " 进入代码快:" + System.currentTimeMillis());
            name = username;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pwd = password;
            System.out.println("PrdService thread name = "+Thread.currentThread().getName()
                    +" 进入代码快:"+System.currentTimeMillis() + "入参 name = " + name + " 入参 pwd = " + pwd);
        }
    }
}

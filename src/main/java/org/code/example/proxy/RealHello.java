package org.code.example.proxy;

/**
 * @ClassName RealHello
 * @Description 真实调用对象
 * @Author Chaiphat
 * @Date 2020/3/4 17:04
 * @Version 1.0
 **/
public class RealHello {

    public String invoke(){
        return "i am proxy";
    }

}

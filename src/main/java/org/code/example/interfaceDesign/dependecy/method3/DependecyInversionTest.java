package org.code.example.interfaceDesign.dependecy.method3;

/**
 * @ClassName DependecyInversionTest
 * @Description 
 * @Author Chaiphat
 * @Date 2020/6/15 15:43
 * @Version 1.0
 **/
public class DependecyInversionTest {

    public static void main(String[] args) {
        // 方法2，通过setter方式实现。
        ChangHong changHong = new ChangHong();
        OpenAndClose openAndClose = new OpenAndClose();
        // 通过setter方式传递
        openAndClose.setItv(changHong);
        openAndClose.open();
    }
    
}

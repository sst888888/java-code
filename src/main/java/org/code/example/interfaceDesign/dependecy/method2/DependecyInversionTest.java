package org.code.example.interfaceDesign.dependecy.method2;

/**
 * @ClassName DependecyInversionTest
 * @Description 
 * @Author Chaiphat
 * @Date 2020/6/15 15:43
 * @Version 1.0
 **/
public class DependecyInversionTest {

    public static void main(String[] args) {
        // 方法2，通过构造器实现。
        ChangHong changHong = new ChangHong();
        OpenAndClose openAndClose = new OpenAndClose(changHong);
        // 通过构造器实现
        openAndClose.open();
    }
    
}

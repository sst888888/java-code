package org.code.example.interfaceDesign.dependecy.method1;

/**
 * @ClassName DependecyInversionTest
 * @Description 
 * @Author Chaiphat
 * @Date 2020/6/15 15:43
 * @Version 1.0
 **/
public class DependecyInversionTest {

    public static void main(String[] args) {
        // 方法一，通过接口实现。
        ChangHong changHong = new ChangHong();
        OpenAndClose openAndClose = new OpenAndClose();
        openAndClose.open(changHong);
    }
    
}

package org.code.example.interfaceDesign.dependecy.method1;

/**
 * @ClassName OpenAndClose
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/15 15:41
 * @Version 1.0
 **/
public class OpenAndClose implements IOpenAndClose{


    @Override
    public void open(ITV itv) {
        itv.play();
    }
}

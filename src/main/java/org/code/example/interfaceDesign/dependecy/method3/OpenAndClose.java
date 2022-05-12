package org.code.example.interfaceDesign.dependecy.method3;

/**
 * @ClassName OpenAndClose
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/15 15:41
 * @Version 1.0
 **/
public class OpenAndClose implements IOpenAndClose {

    public ITV itv;

    public void setItv(ITV itv) {
        this.itv = itv;
    }

    @Override
    public void open() {
        this.itv.play();
    }
}

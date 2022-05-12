package org.code.example.interfaceDesign.ocp_button.demo;

/**
 * @ClassName OcpTest
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/15 17:08
 * @Version 1.0
 **/
public class OcpTest {

    public static void main(String[] args) {
        // 遵循OCP 原则
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
        graphicEditor.drawShape(new Other());
    }
}

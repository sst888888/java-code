package org.code.example.designpatterns.frontControllerPattern;

/**
 * @ClassName Main
 * @Description
 * @Author Chaiphat
 * @Date 2020/1/14 21:42
 * @Version 1.0
 **/
public class Main {

    public static void main(String[] args) {
        // 创建控制器，统一检票口
        TrainFrontController trainFrontController = new TrainFrontController();
        // 验证左边大厅的车票
        trainFrontController.dispatchPeople("left");
        // 验证右边大厅的车票
        trainFrontController.dispatchPeople("right");
    }
}

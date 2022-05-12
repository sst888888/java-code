package org.code.example.designpatterns.frontControllerPattern;

/**
 * @ClassName TrainFrontController
 * @Description 火车统一检票站
 * @Author Chaiphat
 * @Date 2020/1/14 21:17
 * @Version 1.0
 **/
public class TrainFrontController {
    private TrainDispatcher trainDispatcher;

    public TrainFrontController() {
        trainDispatcher = new TrainDispatcher();
    }

    /**
     * @Description 检票，检查车票的有效性
     * @param ticket 车票
     * @return 检票结果
     */
    private boolean ticketCheck(String ticket){
        System.out.println("验证车票：" + ticket);
        return Boolean.TRUE;
    }

    /**
     * @Description 统一方法处理
     * @param ticket 车票
     */
    public void dispatchPeople(String ticket){
        if(ticketCheck(ticket)){
            trainDispatcher.dispatch(ticket);
        }

    }

}

package org.code.example.designpatterns.frontControllerPattern;

/**
 * @ClassName TrainDispatcher
 * @Description 调度器
 * @Author Chaiphat
 * @Date 2020/1/14 21:19
 * @Version 1.0
 **/
public class TrainDispatcher {

    private LeftHall leftHall;
    private RightHall rightHall;

    public TrainDispatcher() {
        leftHall = new LeftHall();
        rightHall = new RightHall();
    }

    /**
     *
     * @Description 调度方法
     * @param ticket 车票
     */
    public void dispatch(String ticket){
        if(ticket.equalsIgnoreCase("left")){
            leftHall.toHall();
        }else{
            rightHall.toHall();
        }
    }


}

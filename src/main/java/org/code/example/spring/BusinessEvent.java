package org.code.example.spring;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName BusinessEvent
 * @Description spring监听机制中的事件
 * @Author Chaiphat
 * @Date 2020/5/3 14:48
 * @Version 1.0
 **/
public class BusinessEvent extends ApplicationEvent {


    //事件的类型
    private String type;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     *               即事件是在哪个对象上发生的
     */
    public BusinessEvent(Object source, String type) {
        super(source);
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}

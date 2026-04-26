package com.org.creational.prototype.deepCopy;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
public class PromotionEvent implements Cloneable {
    private String name;
    private Date startDate;
    private Date endDate;
    private List<PromotionRule> rules;
    // 省略构造函数、getter和setter方法

    // 在促销活动中的clone方法需要克隆里边所有的非基础数据类型
    @Override
    protected PromotionEvent clone() {
        try {
            PromotionEvent clonedEvent = (PromotionEvent) super.clone();
            clonedEvent.startDate = (Date) startDate.clone();
            clonedEvent.endDate = (Date) endDate.clone();
            clonedEvent.rules = new ArrayList<>();
            for (PromotionRule rule : rules) {
                clonedEvent.rules.add(rule.clone());
            }
            return clonedEvent;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

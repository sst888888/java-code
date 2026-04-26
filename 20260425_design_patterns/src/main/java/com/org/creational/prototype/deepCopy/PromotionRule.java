package com.org.creational.prototype.deepCopy;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PromotionRule implements Cloneable {
    private String type;
    private double discount;
    private Product product;
    // 省略构造函数、getter和setter方法

    @Override
    protected PromotionRule clone() {
        try {
            PromotionRule promotionRule = (PromotionRule) super.clone();
            if (null != product) {
                Product product = (Product) product.clone();
                promotionRule.setProduct(product);
            }

            return promotionRule;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
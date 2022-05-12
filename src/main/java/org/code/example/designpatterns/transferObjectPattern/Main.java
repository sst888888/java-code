package org.code.example.designpatterns.transferObjectPattern;

/**
 * @ClassName Main
 * @Description
 * @Author Chaiphat
 * @Date 2020/1/9 17:28
 * @Version 1.0
 **/
public class Main {

    public static void main(String[] args) {
        Express express = new Express();
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setMobile("003");
        goodsInfo.setName("python");
        express.insert(goodsInfo);
        express.getAllGoods().forEach((goods)->{
            System.out.println("手机号：" + goods.getMobile()
            + " | 快递物品：" + goods.getName());
        });
    }

}

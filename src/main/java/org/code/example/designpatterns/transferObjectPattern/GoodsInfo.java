package org.code.example.designpatterns.transferObjectPattern;

/**
 * @ClassName GoodsInfo
 * @Description 物品表
 * @Author Chaiphat
 * @Date 2020/1/9 17:15
 * @Version 1.0
 **/
public class GoodsInfo {

    private String mobile;
    private String name;

    public GoodsInfo(){

    }
    public GoodsInfo(String mobile, String name) {
        this.mobile = mobile;
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package org.code.example.designpatterns.transferObjectPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName Express
 * @Description 快递业务类
 * @Author Chaiphat
 * @Date 2020/1/9 17:18
 * @Version 1.0
 **/
public class Express {
    /**
     * 所有的快递物品存储容器
     */
    private HashMap<String,String> goods;

    Express(){
        goods = new HashMap<>();
        goods.put("001","java");
        goods.put("002","mysql");
    }

    public void insert(GoodsInfo goodsInfo){
        goods.put(goodsInfo.getMobile(),goodsInfo.getName());
    }

    public List<GoodsInfo> getAllGoods(){
        ArrayList<GoodsInfo> arrayList = new ArrayList<>();
        goods.forEach((k,v)->{
            arrayList.add(new GoodsInfo(k,v));
        });
        return arrayList;
    }

}

package decrot.packet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName App
 * @Description
 * @Author Chaiphat
 * @Date 2020/8/3 16:00
 * @Version 1.0
 **/
public class App {
    volatile int counter=0;

    public static void main( String[] args ) throws InterruptedException, IOException
    {
        Order order = new Order();
        init(order);

        for(OrderDetail orderDetail: order.getList()) {
            BigDecimal payMoney = PromotionFactory.getPayMoney(orderDetail);
            orderDetail.setPayMoney(payMoney);
            System.out.println("最终支付金额：" + orderDetail.getPayMoney());
        }
    }


    private static Order init(Order order) {
        Map<PromotionType, SupportPromotions> supportlist = new HashMap<>();

        UserCoupon userCoupon= new UserCoupon();
        userCoupon.setCoupon(new BigDecimal(3));
        userCoupon.setSku("aaa1111");
        userCoupon.setUserId(11);

        SupportPromotions supportPromotions = new SupportPromotions();
        supportPromotions.setPromotionType(PromotionType.COUPON);
        supportPromotions.setPriority(1);
        supportPromotions.setUserCoupon(userCoupon);
        supportlist.put(PromotionType.COUPON, supportPromotions);



        UserRedPacket userRedPacket= new UserRedPacket();
        userRedPacket.setId(1);
        userRedPacket.setRedPacket(new BigDecimal(10));
        userRedPacket.setSku("aaa1111");
        userCoupon.setUserId(11);

        SupportPromotions supportPromotions1 = supportPromotions.clone();
        supportPromotions1.setPromotionType(PromotionType.REDPACKED);
        supportPromotions1.setPriority(2);
        supportPromotions1.setUserRedPacket(userRedPacket);
        supportlist.put(PromotionType.REDPACKED, supportPromotions1);


        Merchandise merchandise = new Merchandise();
        merchandise.setSku("aaa1111");
        merchandise.setName("苹果");
        merchandise.setPrice(new BigDecimal(20));
        merchandise.setSupportPromotions(supportlist);


        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(1);
        orderDetail.setOrderId(1111);
        orderDetail.setMerchandise(merchandise);

        List<OrderDetail> OrderDetailList = new ArrayList<>();
        OrderDetailList.add(orderDetail);

        order.setList(OrderDetailList);
        return order;
    }

}

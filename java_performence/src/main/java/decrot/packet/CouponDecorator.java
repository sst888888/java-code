package decrot.packet;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @ClassName CouponDecorator
 * @Description 计算使用优惠券后的金额
 * @Author Chaiphat
 * @Date 2020/8/3 15:33
 * @Version 1.0
 **/
@Slf4j
public class CouponDecorator extends decrot.packet.BaseCountDecorator {
    public CouponDecorator(IBaseCount count) {
        super(count);
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        super.countPayMoney(orderDetail);
        return countCouponPayMoney(orderDetail);
    }

    private BigDecimal countCouponPayMoney(OrderDetail orderDetail) {
        BigDecimal coupon =  orderDetail.getMerchandise().getSupportPromotions().get(PromotionType.COUPON).getUserCoupon().getCoupon();
        log.info("优惠券金额：" + coupon);

        orderDetail.setPayMoney(orderDetail.getPayMoney().subtract(coupon));
        return orderDetail.getPayMoney();
    }
}

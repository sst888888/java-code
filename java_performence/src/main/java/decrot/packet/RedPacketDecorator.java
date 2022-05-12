package decrot.packet;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @ClassName RedPacketDecorator
 * @Description 计算使用红包后的金额
 * @Author Chaiphat
 * @Date 2020/8/3 15:41
 * @Version 1.0
 **/
@Slf4j
public class RedPacketDecorator extends BaseCountDecorator {

    public RedPacketDecorator(IBaseCount count) {
        super(count);
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        // 层级调用 先执行父级的方法
        super.countPayMoney(orderDetail);
        return countCouponPayMoney(orderDetail);
    }

    private BigDecimal countCouponPayMoney(OrderDetail orderDetail) {
        BigDecimal redPacket = orderDetail.getMerchandise().getSupportPromotions().get(PromotionType.REDPACKED).getUserRedPacket().getRedPacket();
        log.info("红包优惠金额：" + redPacket);
        orderDetail.setPayMoney(orderDetail.getPayMoney().subtract(redPacket));
        return orderDetail.getPayMoney();
    }
}

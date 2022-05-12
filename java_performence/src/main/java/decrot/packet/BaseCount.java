package decrot.packet;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @ClassName BaseCount
 * @Description 支付基本类
 * @Author Chaiphat
 * @Date 2020/8/3 15:22
 * @Version 1.0
 **/
@Slf4j
public class BaseCount implements IBaseCount {
    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        orderDetail.setPayMoney(orderDetail.getMerchandise().getPrice());
        log.info("商品原单价金额为：" + orderDetail.getPayMoney());
        return orderDetail.getPayMoney();
    }
}

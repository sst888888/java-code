package decrot.packet;

import java.math.BigDecimal;

/**
 * @ClassName BaseCountDecorator
 * @Description 计算支付金额的抽象类
 * @Author Chaiphat
 * @Date 2020/8/3 15:26
 * @Version 1.0
 **/
public abstract class BaseCountDecorator implements IBaseCount {
    private IBaseCount count;

    public BaseCountDecorator(IBaseCount count) {
        this.count = count;
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        BigDecimal payTotalMoney = BigDecimal.ZERO;
        if(count != null) {
            payTotalMoney = count.countPayMoney(orderDetail);
        }
        return payTotalMoney;
    }
}

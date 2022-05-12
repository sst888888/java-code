package decrot.packet;

import java.math.BigDecimal;

/**
 * @ClassName OrderDetail
 * @Description 详细订单
 * @Author Chaiphat
 * @Date 2020/8/3 15:06
 * @Version 1.0
 **/
public class OrderDetail {
    private int id;
    private int orderId;
    private Merchandise merchandise;
    private BigDecimal payMoney;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Merchandise getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(Merchandise merchandise) {
        this.merchandise = merchandise;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }
}

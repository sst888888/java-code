package decrot.packet;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName Order
 * @Description 主订单
 * @Author Chaiphat
 * @Date 2020/8/3 15:05
 * @Version 1.0
 **/
public class Order {
    private int id;
    private String orderNo;
    private BigDecimal totalPayMoney;
    private List<OrderDetail> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getTotalPayMoney() {
        return totalPayMoney;
    }

    public void setTotalPayMoney(BigDecimal totalPayMoney) {
        this.totalPayMoney = totalPayMoney;
    }

    public List<OrderDetail> getList() {
        return list;
    }

    public void setList(List<OrderDetail> list) {
        this.list = list;
    }
}

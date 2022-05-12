package decrot.packet;

import java.math.BigDecimal;

/**
 * @ClassName UserRedPacket
 * @Description 红包
 * @Author Chaiphat
 * @Date 2020/8/3 15:15
 * @Version 1.0
 **/
public class UserRedPacket {
    private int id;
    private int userId;
    private String sku;
    // 领取红包金额
    private BigDecimal redPacket;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getRedPacket() {
        return redPacket;
    }

    public void setRedPacket(BigDecimal redPacket) {
        this.redPacket = redPacket;
    }
}

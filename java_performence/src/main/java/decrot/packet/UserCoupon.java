package decrot.packet;

import java.math.BigDecimal;

/**
 * @ClassName UserCoupon
 * @Description 优惠券
 * @Author Chaiphat
 * @Date 2020/8/3 15:12
 * @Version 1.0
 **/
public class UserCoupon {
    private int id;
    private int userId;
    // 商品sku
    private String sku;
    // 优惠金额
    private BigDecimal coupon;

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

    public BigDecimal getCoupon() {
        return coupon;
    }

    public void setCoupon(BigDecimal coupon) {
        this.coupon = coupon;
    }
}

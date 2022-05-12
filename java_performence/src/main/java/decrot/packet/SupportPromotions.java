package decrot.packet;

/**
 * @ClassName SupportPromotions
 * @Description 促销类型
 * @Author Chaiphat
 * @Date 2020/8/3 15:09
 * @Version 1.0
 **/
public class SupportPromotions implements Cloneable{
    private int id;
    // 促销类型 1 优惠券 2 红包
    private PromotionType promotionType;
    private int priority;
    private UserCoupon userCoupon;
    private UserRedPacket userRedPacket;

    @Override
    public SupportPromotions clone() {
        SupportPromotions supportPromotions = null;
        try {
            supportPromotions = (SupportPromotions)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return supportPromotions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(PromotionType promotionType) {
        this.promotionType = promotionType;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public UserCoupon getUserCoupon() {
        return userCoupon;
    }

    public void setUserCoupon(UserCoupon userCoupon) {
        this.userCoupon = userCoupon;
    }

    public UserRedPacket getUserRedPacket() {
        return userRedPacket;
    }

    public void setUserRedPacket(UserRedPacket userRedPacket) {
        this.userRedPacket = userRedPacket;
    }
}

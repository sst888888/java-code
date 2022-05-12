package decrot.packet;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @ClassName Merchandise
 * @Description 商品
 * @Author Chaiphat
 * @Date 2020/8/3 15:07
 * @Version 1.0
 **/
public class Merchandise {
    private String sku;
    private String name;
    private BigDecimal price;
    // 支持促销类型
    private Map<PromotionType, SupportPromotions> supportPromotions;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Map<PromotionType, SupportPromotions> getSupportPromotions() {
        return supportPromotions;
    }

    public void setSupportPromotions(Map<PromotionType, SupportPromotions> supportPromotions) {
        this.supportPromotions = supportPromotions;
    }
}

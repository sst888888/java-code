package tool;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class ShardingRange {
    private LocalDateTime createdTime;
    private LocalDateTime beginCreatedTime;
    private LocalDateTime endCreatedTime;

    private ShardingRange(LocalDateTime createdTime, LocalDateTime beginCreatedTime, LocalDateTime endCreatedTime) {
        this.createdTime = createdTime;
        this.beginCreatedTime = beginCreatedTime;
        this.endCreatedTime = endCreatedTime;
    }

    public static ShardingRange ofSerialNumber(String serialNumber) {
        LocalDateTime createdTime = ShardingUtil.ofSerialNumber(serialNumber);
        return new ShardingRange(createdTime, createdTime.minusSeconds(1L), createdTime.plusSeconds(1L));
    }

    public LocalDateTime getCreatedTime() {
        return this.createdTime;
    }

    public LocalDateTime getBeginCreatedTime() {
        return this.beginCreatedTime;
    }

    public LocalDateTime getEndCreatedTime() {
        return this.endCreatedTime;
    }

    public void setCreatedTime(final LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public void setBeginCreatedTime(final LocalDateTime beginCreatedTime) {
        this.beginCreatedTime = beginCreatedTime;
    }

    public void setEndCreatedTime(final LocalDateTime endCreatedTime) {
        this.endCreatedTime = endCreatedTime;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ShardingRange)) {
            return false;
        } else {
            ShardingRange other = (ShardingRange)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$createdTime = this.getCreatedTime();
                    Object other$createdTime = other.getCreatedTime();
                    if (this$createdTime == null) {
                        if (other$createdTime == null) {
                            break label47;
                        }
                    } else if (this$createdTime.equals(other$createdTime)) {
                        break label47;
                    }

                    return false;
                }

                Object this$beginCreatedTime = this.getBeginCreatedTime();
                Object other$beginCreatedTime = other.getBeginCreatedTime();
                if (this$beginCreatedTime == null) {
                    if (other$beginCreatedTime != null) {
                        return false;
                    }
                } else if (!this$beginCreatedTime.equals(other$beginCreatedTime)) {
                    return false;
                }

                Object this$endCreatedTime = this.getEndCreatedTime();
                Object other$endCreatedTime = other.getEndCreatedTime();
                if (this$endCreatedTime == null) {
                    if (other$endCreatedTime != null) {
                        return false;
                    }
                } else if (!this$endCreatedTime.equals(other$endCreatedTime)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ShardingRange;
    }

    public int hashCode() {
        int result = 1;
        Object $createdTime = this.getCreatedTime();
        result = result * 59 + ($createdTime == null ? 43 : $createdTime.hashCode());
        Object $beginCreatedTime = this.getBeginCreatedTime();
        result = result * 59 + ($beginCreatedTime == null ? 43 : $beginCreatedTime.hashCode());
        Object $endCreatedTime = this.getEndCreatedTime();
        result = result * 59 + ($endCreatedTime == null ? 43 : $endCreatedTime.hashCode());
        return result;
    }

    public String toString() {
        return "ShardingRange(createdTime=" + this.getCreatedTime() + ", beginCreatedTime=" + this.getBeginCreatedTime() + ", endCreatedTime=" + this.getEndCreatedTime() + ")";
    }
}

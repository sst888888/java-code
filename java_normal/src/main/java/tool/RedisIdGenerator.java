package tool;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisIdGenerator {

    private static final String DEFAULT_REDIS_COUNTER_KEY = "ddpay-counter";
    private RedisAtomicLong atomicLong;

    public RedisIdGenerator(RedisConnectionFactory redisConnectionFactory) {
        this.atomicLong = new RedisAtomicLong("ddpay-counter", redisConnectionFactory);
    }

    public Long nextId() {
        Long incr = this.atomicLong.getAndIncrement();
        if (null == incr || incr == 0L) {
            this.atomicLong.expire(24L, TimeUnit.HOURS);
            incr = this.atomicLong.getAndIncrement();
        }

        return incr;
    }

}

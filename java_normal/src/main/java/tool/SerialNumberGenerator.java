package tool;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class SerialNumberGenerator {
    private RedisIdGenerator redisIdGenerator;

    public SerialNumberGenerator(RedisIdGenerator redisIdGenerator) {
        this.redisIdGenerator = redisIdGenerator;
    }

    public String generate(String prefix) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String dateStr = LocalDateTime.now().format(formatter);
        return String.format("%s%s%08d", prefix, dateStr, this.redisIdGenerator.nextId());
    }

    public static void main(String[] args) {
        SerialNumberGenerator serialNumberGenerator = new SerialNumberGenerator(new RedisIdGenerator(new JedisConnectionFactory()));
        String id = serialNumberGenerator.generate("cs");
        log.info("id is {}", id);

        ShardingUtil.ofSerialNumber(id);

    }
}

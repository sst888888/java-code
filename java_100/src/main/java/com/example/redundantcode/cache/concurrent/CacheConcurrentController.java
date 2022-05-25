package com.example.redundantcode.cache.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequestMapping("cache-concurrent")
@RestController
public class CacheConcurrentController {
    // 同步关键类，构造方法传入的数字是多少，则同一个时刻，只运行多少个进程同时运行指定代码
    private final Semaphore semaphore = new Semaphore(10);

    private final AtomicInteger atomicInteger = new AtomicInteger();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @PostConstruct
    public void init() {
        stringRedisTemplate.opsForValue().set("hotsopt", getExpensiveData(), 5, TimeUnit.SECONDS);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> log.info("DB QPS : {}",
                atomicInteger.getAndSet(0)), 0, 1, TimeUnit.SECONDS);
    }

    @GetMapping("wrong")
    public String wrong() {
        String data = stringRedisTemplate.opsForValue().get("hotsopt");
        if (StringUtils.isEmpty(data)) {
            data = getExpensiveData();
            stringRedisTemplate.opsForValue().set("hotsopt", data, 5, TimeUnit.SECONDS);
        }
        return data;
    }

    /**
     * 这样虽然可以把数据库回源并发降到最低，但也限制了缓存失效时的并发。
     * @return
     */
    @GetMapping("right")
    public String right() {
        String data = stringRedisTemplate.opsForValue().get("hotsopt");
        if (StringUtils.isEmpty(data)) {
            RLock locker = redissonClient.getLock("locker");
            if (locker.tryLock()) {
                try {
                    data = stringRedisTemplate.opsForValue().get("hotsopt");
                    if (StringUtils.isEmpty(data)) {
                        data = getExpensiveData();
                        stringRedisTemplate.opsForValue().set("hotsopt", data, 5, TimeUnit.SECONDS);
                    }
                } finally {
                    locker.unlock();
                }
            }
        }
        return data;
    }

    /**
     * 不使用锁进行限制，而是使用类似 Semaphore 的工具限制并发数，
     * 比如限制为 10，这样既限制了回源并发数不至于太大，又能使得一定量的线程可以同时回源。
     * @return
     * @throws InterruptedException
     */
    @GetMapping("right2")
    public String right2() throws InterruptedException {
        semaphore.acquire();
        String data = stringRedisTemplate.opsForValue().get("hotsopt");
        if (StringUtils.isEmpty(data)) {
            data = stringRedisTemplate.opsForValue().get("hotsopt");
            if (StringUtils.isEmpty(data)) {
                data = getExpensiveData();
                stringRedisTemplate.opsForValue().set("hotsopt", data, 5, TimeUnit.SECONDS);
            }
        }
        semaphore.release();
        return data;
    }

    private String getExpensiveData() {
        atomicInteger.incrementAndGet();
        return "important data";
    }
}

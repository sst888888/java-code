package com.example.oom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

@RestController
@RequestMapping("oom-weak")
@Slf4j
public class WeakHashMapOOMController {
    private final Map<User, UserProfile> cache = new WeakHashMap<>();
    private final Map<User, WeakReference<UserProfile>> cache2 = new WeakHashMap<>();
    private final Map<User, UserProfile> cache3 = new ConcurrentReferenceHashMap<>();

    @GetMapping("wrong")
    @Test
    public void wrong() {
        String userName = "sst";
        // 间隔1秒定时输出缓存中的条目数
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                () -> log.info("cache size:{}", cache.size()), 1, 1, TimeUnit.SECONDS);
        LongStream.rangeClosed(1, 20000000).forEach(i -> {
            User user = new User(userName + i);
            cache.put(user, new UserProfile(user, "location" + i));
        });
    }

    @GetMapping("right")
    @Test
    public void right() {
        String userName = "sst";
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                () -> log.info("cache size:{}", cache2.size()), 1, 1, TimeUnit.SECONDS);
        LongStream.rangeClosed(1, 20000000).forEach(i -> {
            User user = new User(userName + i);
            // 这次，我们使用弱引用来包装UserProfile
            cache2.put(user, new WeakReference(new UserProfile(user, "location" + i)));
        });
    }

    @GetMapping("right2")
    public void right2() {
        String userName = "sst";
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                () -> log.info("cache size:{}", cache.size()), 1, 1, TimeUnit.SECONDS);
        LongStream.rangeClosed(1, 2000000).forEach(i -> {
            User user = new User(userName + i);
            // 让 Value 也就是 UserProfile 不再引用 Key，而是重新 new 出一个新的 User 对象赋值给 UserProfile
            cache.put(user, new UserProfile(new User(user.getName()), "location" + i));
        });
    }

    @GetMapping("right3")
    public void right3() {
        String userName = "sst";
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                () -> log.info("cache size:{}", cache3.size()), 1, 1, TimeUnit.SECONDS);
        LongStream.rangeClosed(1, 20000000).forEach(i -> {
            User user = new User(userName + i);
            cache3.put(user, new UserProfile(user, "location" + i));
        });
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class User {
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class UserProfile {
        private User user;
        private String location;
    }
}

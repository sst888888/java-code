package com.util;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RedisShardedPool
 * @Description 集群下的连接池
 * @Author Chaiphat
 * @Date 2020/10/18 15:43
 * @Version 1.0
 **/
public class RedisShardedPool {
    private static ShardedJedisPool pool; // Sharded jedis连接池
//    private static Integer maxTotal = Integer.valueOf(PropertiesUtil.getProperty("redis.max.total", "20"));
//    private static Integer maxIdle = Integer.valueOf(PropertiesUtil.getProperty("redis.max.idle", "10"));
//    private static Integer minIdle = Integer.valueOf(PropertiesUtil.getProperty("redis.min.idle", "2"));
//    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow ", "true"));
//    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return", "true"));


    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        config.setMinIdle(2);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);

        JedisShardInfo info1 = new JedisShardInfo("192.168.60.125", 6379, 1000 * 2);
        JedisShardInfo info2 = new JedisShardInfo("192.168.60.125", 6379, 1000 * 2);
        info1.setPassword("pwd@123");
        info2.setPassword("pwd@123");
        List<JedisShardInfo> jedisShardInfos = new ArrayList<>(2);
        jedisShardInfos.add(info1);
        jedisShardInfos.add(info2);

        pool = new ShardedJedisPool(config, jedisShardInfos, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
    }


    static {
        initPool();
    }

    public static ShardedJedis getJedis() {
        return pool.getResource();
    }

    public static void returnResource(ShardedJedis jedis) {
        pool.returnResource(jedis);
    }

    public static void main(String[] args) {
        ShardedJedis jedis = pool.getResource();
        for (int i = 0; i < 10; i++) {
            jedis.set("key" + i, "value" + i);
        }
    }
}

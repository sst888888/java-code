package org.zk.config;


import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@Configuration
public class ZookeeperConfig {

    @Value("${zookeeper.address}")
    private String connectString;

    @Value("${zookeeper.timeout}")
    private Integer timeout;

    @Bean(name = "zkClient")
    public ZooKeeper zkClient() throws IOException, InterruptedException {
        ZooKeeper zooKeeper = null;
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        zooKeeper = new ZooKeeper(connectString, timeout, watchedEvent -> {
            if (Watcher.Event.KeeperState.SaslAuthenticated == watchedEvent.getState()) {
                // 收到服务端的SaslAuthenticated响应事件 表示连接成功
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        return zooKeeper;
    }

}

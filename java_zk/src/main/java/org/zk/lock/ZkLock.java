package org.zk.lock;


import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 分布式锁
 */
@Slf4j
public class ZkLock implements Lock {

    private ZooKeeper zk;
    // 锁的根节点
    private String root = "/locks";
    // 锁的名称
    private String lockName;
    // 当前线程创建的临时有序节点
    private ThreadLocal<String> nodeId = new ThreadLocal<>();
    private final static byte[] data = new byte[0];

    public ZkLock(ZooKeeper zooKeeper, String lockName) throws KeeperException, InterruptedException {
        this.zk = zooKeeper;
        this.lockName = lockName;

        Stat stat = zk.exists(root, false);
        if (stat == null) {
            // 创建根节点
            zk.create(root, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    /**
     * 监听器 监听临时节点的删除
     */
    class LockWatcher implements Watcher {
        private CountDownLatch latch;

        public LockWatcher(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void process(WatchedEvent watchedEvent) {
            if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                latch.countDown();
            }
        }
    }

    @Override
    public void lock() {
        // 在根节点下创建临时有序节点
        try {
            String node = zk.create(root + "/" +lockName, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            log.info(Thread.currentThread().getName() + node + " create");

            // 获取根节点的下节点
            List<String> subNodes = zk.getChildren(root, false);

            TreeSet<String> sortedNodes = new TreeSet<>();
            for (String nd : subNodes) {
                sortedNodes.add(root + nd);
            }

            // 获取序号最下的子节点
            String smallNode = sortedNodes.first();

            // 如果该次创建的临时有序节点是最下的子节点 表示获得锁
            if (node.equals(smallNode)) {
                log.info(Thread.currentThread().getName() + node + " get lock");
                this.nodeId.set(node);
            }

            // 不是最下的子节点 获取当前创建节点的前一个节点
            String preNode = sortedNodes.lower(node);
            CountDownLatch latch = new CountDownLatch(1);
            // 查询前一个节点 同时注册监听
            Stat stat = zk.exists(preNode, new LockWatcher(latch));
            // 如果查询节点不存在 无需等待 存在则监听
            if (stat != null) {
                log.info(Thread.currentThread().getName() + node + " waiting for " + root + "/" + preNode + " release lock");
                latch.await();
            }

            this.nodeId.set(node);
        } catch (KeeperException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void unlock() {
        // 释放锁将本次创建节点移除
        log.info(Thread.currentThread().getName() + " unlock");
        if (nodeId != null) {
            try {
                zk.delete(nodeId.get(), -1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (KeeperException e) {
                throw new RuntimeException(e);
            }
        }

        nodeId.remove();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        // TODO document why this method is empty
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}

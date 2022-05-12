package org.code.example.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadLocalDemo {

    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalDemo.class);

    static class DemoClass {
        private ThreadLocal<Integer> i = ThreadLocal.withInitial(() -> 0);

        public Integer get() {
            return i.get();
        }

        public void set(Integer integer) {
            i.set(i.get() + integer);
        }
    }

    public static void main(String[] args) {
        DemoClass demoClass = new DemoClass();
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 100; j++) {
                    demoClass.set(j);
                    logger.info("demoClass.get() == {}", demoClass.get());
                }
            }).start();
        }
    }

}

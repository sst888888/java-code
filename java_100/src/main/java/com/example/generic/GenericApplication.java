package com.example.generic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class GenericApplication {

    public static void main(String[] args) {
      right();
    }

    @Test
    public void wrong1() {
        Child1 child1 = new Child1();
        Arrays.stream(child1.getClass().getMethods())
                .filter(method -> method.getName().equals("setValue"))
                .forEach(method -> {
                    try {
                        method.invoke(child1, "test");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        log.info(child1.toString());
    }

    // getDeclaredMethods 只能获得当前类所有的 public、protected、package 和 private 方法。
    @Test
    public  void wrong2() {
        Child1 child1 = new Child1();
        Arrays.stream(child1.getClass().getDeclaredMethods())
                .filter(method -> method.getName().equals("setValue"))
                .forEach(method -> {
                    try {
                        method.invoke(child1, "test");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        log.info(child1.toString());
    }

    @Test
    public void wrong3() {
        Child2 child2 = new Child2();
        Arrays.stream(child2.getClass().getDeclaredMethods())
                .filter(method -> method.getName().equals("setValue"))
                .forEach(method -> {
                    try {
                        method.invoke(child2, "test");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        log.info(child2.toString());
    }

    public static void right() {
        Child2 child2 = new Child2();
        Arrays.stream(child2.getClass().getDeclaredMethods())
                .filter(method -> method.getName().equals("setValue") && !method.isBridge())
                .findFirst().ifPresent(method -> {
            try {
                method.invoke(child2, "test");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        log.info(child2.toString());
    }
}
@Slf4j
class Parent<T> {

    AtomicInteger updateCount = new AtomicInteger();

    private T value;

    @Override
    public String toString() {
        return String.format("value: %s updateCount: %d", value, updateCount.get());
    }

    public void setValue(T value) {
        log.info("Parent.setValue called");
        this.value = value;
        updateCount.incrementAndGet();
    }
}

@Slf4j
class Child1 extends Parent {

    public void setValue(String value) {
        log.info("Child1.setValue called");
        super.setValue(value);
    }
}

@Slf4j
class Child2 extends Parent<String> {

    @Override
    public void setValue(String value) {
        log.info("Child2.setValue called");
        super.setValue(value);
    }
}
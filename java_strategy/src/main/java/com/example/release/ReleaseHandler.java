package com.example.release;

/**
 * @author: cp
 * @date: 2023-09-24 22:59
 */
public interface ReleaseHandler {

    boolean handle(ReleaseDTO releaseDTO);

    // 抽象类可不实现该方法 但是其子类必须实现
    boolean handle2(ReleaseDTO releaseDTO);

}

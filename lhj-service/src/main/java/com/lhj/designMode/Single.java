package com.lhj.designMode;

import lombok.extern.slf4j.Slf4j;

// （单例）饿汉模式
@Slf4j
public class Single {

    private static final Single SINGLE = new Single();

    private Single() {
        log.error("饿汉模式初始化");
    }

    public Single getInstance() {
        return SINGLE;
    }
}

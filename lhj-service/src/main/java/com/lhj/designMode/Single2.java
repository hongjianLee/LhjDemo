package com.lhj.designMode;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
// （单例）懒汉模式
@Slf4j
public class Single2 {

    private static Single2 single2;

    private Single2() {
    }

    public static synchronized Single2 getInstance() {
        if (Objects.isNull(single2)) {
            log.error("懒汉模式初始化");
            return new Single2();
        }
        return single2;
    }
}

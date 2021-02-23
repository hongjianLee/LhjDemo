package com.lhj.designMode;

public class SinEnum {

    private SinEnum(){

    }

    public static SinEnum getInstance() {
        return sinEnumPattern.INSTANCE.getInstance();
    }

    private static enum sinEnumPattern {
        INSTANCE;
        // 枚举元素为单例
        private SinEnum sinEnum;

        private sinEnumPattern() {
            sinEnum = new SinEnum();
        }

        public SinEnum getInstance() {
            return sinEnum;
        }
    }

    public static void main(String[] args) {
        SinEnum u1 = SinEnum.getInstance();
        SinEnum u2 = SinEnum.getInstance();
        System.out.println(u1 == u2);
    }
}

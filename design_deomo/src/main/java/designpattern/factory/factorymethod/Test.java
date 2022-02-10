package designpattern.factory.factorymethod;

import designpattern.factory.simplefactory.noodles.INoodles;

/**
 * @Description TODO
 * @Author huanghao
 * @Date 2021-4-15
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        LzNoodlesFactory lzNoodlesFactory = new LzNoodlesFactory();
        INoodles noodles = lzNoodlesFactory.createNoodles();
        noodles.desc();
    }
}

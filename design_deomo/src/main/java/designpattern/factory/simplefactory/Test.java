package designpattern.factory.simplefactory;

import designpattern.factory.simplefactory.noodles.INoodles;

/**
 * @Description TODO
 * @Author huanghao
 * @Date 2021-4-15
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        INoodles noodles = SimpleNoodleFactory.createNoodles(2);
        noodles.desc();
    }
}

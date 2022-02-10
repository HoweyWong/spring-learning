package designpattern.factory.factorymethod;

import designpattern.factory.simplefactory.noodles.INoodles;
import designpattern.factory.simplefactory.noodles.LzNoodles;

/**
 * @Description TODO
 * @Author huanghao
 * @Date 2021-4-15
 * @Version 1.0
 */
public class LzNoodlesFactory implements INoodlesFactory {
    @Override
    public INoodles createNoodles() {
        return new LzNoodles();
    }
}

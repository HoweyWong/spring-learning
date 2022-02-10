package designpattern.factory.factorymethod;

import designpattern.factory.simplefactory.noodles.INoodles;
import designpattern.factory.simplefactory.noodles.ReganNoodles;

/**
 * @Description TODO
 * @Author huanghao
 * @Date 2021-4-15
 * @Version 1.0
 */
public class ReganINoodlesFactory implements INoodlesFactory {
    @Override
    public INoodles createNoodles() {
        return new ReganNoodles();
    }
}

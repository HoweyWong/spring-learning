package designpattern.factory.factorymethod;

import designpattern.factory.simplefactory.noodles.INoodles;

/**
 * @Description TODO
 * @Author huanghao
 * @Date 2021-4-15
 * @Version 1.0
 */
public interface INoodlesFactory {
    INoodles createNoodles();
}

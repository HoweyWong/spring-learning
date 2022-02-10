package designpattern.factory.simplefactory;

import designpattern.factory.simplefactory.noodles.INoodles;
import designpattern.factory.simplefactory.noodles.LzNoodles;
import designpattern.factory.simplefactory.noodles.PaoNoodles;
import designpattern.factory.simplefactory.noodles.ReganNoodles;

/**
 * @Description TODO
 * @Author huanghao
 * @Date 2021-4-15
 * @Version 1.0
 */
public class SimpleNoodleFactory {
    public static final int TYPE_LZ = 1;
    public static final int TYPE_PM = 2;
    public static final int TYPE_RG = 3;

    public static INoodles createNoodles(int type) {
        switch (type) {
            case TYPE_LZ:
                return new LzNoodles();
            case TYPE_PM:
                return new PaoNoodles();
            case TYPE_RG:
                return new ReganNoodles();
            default:
                return new LzNoodles();
        }
    }
}

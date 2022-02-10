package designpattern.proxy.dynamicproxy;

/**
 * 委托方（委托对象）
 */
public class RentingHouseImpl implements IRentingHouse {

    @Override
    public void rentHouse() {
        System.out.println("我要租用一室一厅的房子");
    }

    @Override
    public void test() {
        System.out.println("test");
    }
}
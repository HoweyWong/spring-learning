package designpattern.proxy.dynamicproxy;

/**
 * @Description TODO
 * @Author huanghao
 * @Date 2021-4-15
 * @Version 1.0
 */
public class ProxyTest {
    public static void main(String[] args) {
        IRentingHouse rentingHouse = new RentingHouseImpl();


        RentingHouseImpl rentingHouse2 = new RentingHouseImpl();
        RentingHouseImpl cglibProxy = (RentingHouseImpl) ProxyFactory.getInstance().getCglibProxy(rentingHouse2);
        cglibProxy.rentHouse();

        IRentingHouse jdkProxy= (IRentingHouse) ProxyFactory.getInstance().getJdkProxy(rentingHouse2);
        jdkProxy.rentHouse();
    }
}

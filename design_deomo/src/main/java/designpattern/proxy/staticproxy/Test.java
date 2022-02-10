package designpattern.proxy.staticproxy;

/**
 * @Description TODO
 * @Author huanghao
 * @Date 2021-4-15
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        IRentingHouse rentingHouse = new RentingHouseImpl();

        RentingHouseProxy rentingHouseProxy = new RentingHouseProxy(rentingHouse);
        rentingHouseProxy.rentHouse();
    }
}

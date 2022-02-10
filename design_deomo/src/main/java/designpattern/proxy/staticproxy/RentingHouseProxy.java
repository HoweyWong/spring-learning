package designpattern.proxy.staticproxy;

/**
 * @Description TODO
 * @Author huanghao
 * @Date 2021-4-15
 * @Version 1.0
 */
public class RentingHouseProxy implements IRentingHouse {
    private IRentingHouse rentingHouse;

    public RentingHouseProxy(IRentingHouse rentingHouse) {
        this.rentingHouse = rentingHouse;
    }

    /**
     * TODO
     *
     * @return void
     * @Date 2021-4-15 20:30
     */
    @Override
    public void rentHouse() {
        System.out.println("中介代理费3000元");
        rentingHouse.rentHouse();
        System.out.println("客户信息卖了3毛钱");
    }
}

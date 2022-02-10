package designpattern.proxy.staticproxy;

/**
 * @Description TODO
 * @Author huanghao
 * @Date 2021-4-15
 * @Version 1.0
 */
public class RentingHouseImpl implements IRentingHouse {
    /**
     * TODO
     *
     * @return void
     * @Date 2021-4-15 20:30
     */
    @Override
    public void rentHouse() {
        System.out.println("我要租用一房一厅的房子。");
    }
}

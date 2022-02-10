package designpattern.singleton;

/**
 * @Description TODO
 * @Author huanghao
 * @Date 2021-4-16
 * @Version 1.0
 */
public class HungrySingleton {

    // 构造方法私有化
    private HungrySingleton(){}

    // 获得instance
    private static final HungrySingleton instance = new HungrySingleton();

    public static HungrySingleton getInstance(){
        return instance;
    }
}

package designpattern.singleton;

/**
 * @Description TODO
 * @Author huanghao
 * @Date 2021-4-16
 * @Version 1.0
 */
public class LazySingleton {
    private LazySingleton() {
    }
    private static LazySingleton instance;

    // synchronize实现同步，避免判空时线程不安全
    public static synchronized LazySingleton getInstance(){
        // 只有不存在该实例才创建
        if (instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
}

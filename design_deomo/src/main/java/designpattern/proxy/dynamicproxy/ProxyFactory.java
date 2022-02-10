package designpattern.proxy.dynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description TODO
 * @Author huanghao
 * @Date 2021-4-15
 * @Version 1.0
 */
public class ProxyFactory {
    private ProxyFactory(){}
    private static ProxyFactory proxyFactory = new ProxyFactory();
    public static ProxyFactory getInstance(){return proxyFactory;}

    public Object getJdkProxy(Object obj){
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return getObject(method, args, obj);
            }
        });
    }

    public Object getCglibProxy(Object obj){
        return Enhancer.create(obj.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                return getObject(method, args, obj);
            }
        });
    }

    private Object getObject(Method method, Object[] args, Object obj) throws IllegalAccessException, InvocationTargetException {
        Object object = null;
        System.out.println("中介收费3000|");
        object = method.invoke(obj, args);
        System.out.println("客户信息3毛");
        return object;
    }
}

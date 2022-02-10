package com.lagou.edu.framework.loader;

import com.lagou.edu.factory.ProxyFactory;
import com.lagou.edu.framework.annotation.MyAutowired;
import com.lagou.edu.framework.annotation.MyRepository;
import com.lagou.edu.framework.annotation.MyService;
import com.lagou.edu.framework.annotation.MyTransactional;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author huanghao
 * @Date 2021-4-20
 * @Version 1.0
 */
public class ContextLoader {
    // 1、加载解析xml，读取xml中的scan信息到classname，通过反射技术实例化bean对象，然后放⼊map待⽤
    /**
     * TODO
     */
    private static Map<String, Object> map = new HashMap<>();
    // 扫描后的类集合
    private static List<String> classname = new ArrayList<String>();
    // 需要事务控制的集合
    private static Map<String, Object> transMap = new HashMap<>();

    static {
        // 加载配置文件
        InputStream resourceAsStream = ContextLoader.class.getClassLoader().getResourceAsStream("beans.xml");
        // 解析配置文件
        SAXReader reader = new SAXReader();
        try {
            // 读取文件对象
            Document document = reader.read(resourceAsStream);
            // 获得根节点
            Element rootElement = document.getRootElement();
            // scan,//表示任意匹配的读取
            List<Element> scanList = rootElement.selectNodes("//scan");
            // 遍历加载,获得id和class
            for (Element element : scanList) {
                // 获得扫描包路径
                String baseDir = element.attributeValue("base-package");

                doScanner(baseDir);

                //3、初始化所有相关类
                doInstance();

                //4、自动注入
                doAutowired();

                //5、事务控制
                doTransaction();
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    /**
     * 提供接⼝⽅法根据id从map中获取bean（静态⽅法）
     *
     * @param id
     * @return java.lang.Object
     * @Date 2021-4-13 22:38
     */
    public static Object getBean(String id) {
        return map.get(id);
    }

    private static void doScanner(String scanPackage) {
        URL url = ContextLoader.class.getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                // 扫描把类全路径加载到list中
                String className = scanPackage + "." + file.getName().replace(".class", "");
                classname.add(className);
            }
        }
    }

    private static void doTransaction() {
        if (transMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : transMap.entrySet()) {
            // entry为全类名，value为benaname
            // 取出beanName
            String beanName = (String) entry.getValue();

            // 用代理对象创建对象
            // 获得代理对象
            ProxyFactory proxyFactory = (ProxyFactory) map.get("proxyFactory");
            Object proxy;
            // 判断有无实现接口，无则使用cglib
            if (map.get(beanName).getClass().getInterfaces().length==0){
                proxy = proxyFactory.getCglibProxy(map.get(beanName));
            }else {
                proxy = proxyFactory.getJDKProxy(map.get(beanName));
            }

            // 根据beanName重新往map put值
            map.put(beanName, proxy);
        }
    }

    /**
     * 实例化类
     */
    private static void doInstance() {
        if (classname.isEmpty()) {
            return;
        }
        for (String s : classname) {
            try {
                Class<?> clazz = Class.forName(s);
                if (clazz.isAnnotationPresent(MyService.class) || clazz.isAnnotationPresent(MyRepository.class)) {
                    String beanName;
                    // 获得注解对象
                    // 注解中设置的值作为beanName
                    if (clazz.isAnnotationPresent(MyService.class)) {
                        MyService annotation = clazz.getAnnotation(MyService.class);
                        beanName = annotation.value();
                    } else {
                        MyRepository annotation = clazz.getAnnotation(MyRepository.class);
                        beanName = annotation.value();
                    }
                    // 如果未设置，则默认值类名首字母小写,即注解value无值
                    if ("".equals(beanName.trim())) {
                        beanName = lowerFirstCase(clazz.getSimpleName());
                    }
                    // 如有事务，加入
                    if (clazz.isAnnotationPresent(MyTransactional.class)) {
                        transMap.put(s, beanName);
                    }
                    Object instance = clazz.newInstance();

                    map.put(beanName, instance);
                    // 3、根据接口类型来赋值（可能有重复）
                    for (Class<?> i : clazz.getInterfaces()) {
                        map.put(i.getName(), instance);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 依赖注入
     */
    private static void doAutowired() {
        if (map.isEmpty()) {
            return;
        }
        // 遍历map
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            // 取得字段
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            // 遍历字段，是否有配置注入的注解
            for (Field field : fields) {
                if (!field.isAnnotationPresent(MyAutowired.class)) {
                    continue;
                }
                MyAutowired annotation = field.getAnnotation(MyAutowired.class);
                String beanName = annotation.value().trim();
                if ("".equals(beanName)) {
                    beanName = field.getType().getName();
                }
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(), map.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

    private static String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}

package com.lagou.edu.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author huanghao
 * @Date 2021-4-13
 * @Version 1.0
 */
public class BeanFactory {
    // 1、加载解析xml，读取xml中的bean信息，通过反射技术实例化bean对象，然后放⼊
    // map待⽤
    /**
     * TODO
     */
    private static Map<String,Object> map = new HashMap<>();

    static {
        // 加载配置文件
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
        // 解析配置文件
        SAXReader reader = new SAXReader();
        try {
            // 读取文件对象
            Document document = reader.read(resourceAsStream);
            // 获得根节点
            Element rootElement = document.getRootElement();
            // 读取bean节点,//表示任意匹配的读取，.表示上级
            List<Element> beanList = rootElement.selectNodes("//bean");
            // 遍历加载,获得id和class
            for (Element element : beanList) {
                // accountDao，作为唯一id
                String id = element.attributeValue("id");
                // com.lagou.edu.dao.impl.JdbcAccountDaoImpl 实现类全路径，用以反射创建
                String clazz = element.attributeValue("class");
                // 反射实例化对象
                Class<?> aClass = Class.forName(clazz);
                Object o = aClass.newInstance();
                // 存到map
                map.put(id,o);
            }

            // 注入依赖对象
            // 根据有无property判断
            List<Element> propertyList = rootElement.selectNodes("//property");
            // 遍历解析property
            for (Element element : propertyList) {
                // 解析name AccountDao，ref accountDao
                String name = element.attributeValue("name");
                String ref = element.attributeValue("ref");

                // 获得父元素，即需要维护依赖的bean
                Element parent = element.getParent();
                // 从父对象中根据id取出容器中的对象
                Object parentObject = map.get(parent.attributeValue("id"));
                Method[] methods = parentObject.getClass().getMethods();
                // 遍历方法，找到set，把子对象注入
                for (Method method : methods) {
                    // setAccountDao
                    if (method.getName().equalsIgnoreCase("set"+name)){
                        // TransferServiceImpl.setAccountDao(JdbcAccountDaoImpl)
                        // 父对象中有set方法，把依赖的对象实例化进去
                        method.invoke(parentObject,map.get(ref));
                    }
                }
                // 把父对象进行了set值，依赖注入的对象重新放进map
                map.put(parent.attributeValue("id"),parentObject);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * TODO 提供接⼝⽅法根据id从map中获取bean（静态⽅法）
     *
     * @Date 2021-4-13 22:38
     * @param id
     * @return java.lang.Object
     */
    public static Object getBean(String id){return map.get(id);}
}

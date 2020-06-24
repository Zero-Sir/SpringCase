package org.ioc.context;

import org.ioc.annotation.SelfAutowired;
import org.ioc.annotation.SelfComponent;
import org.ioc.util.ClassParseUtil;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SelfPathApplicationContext {

    // 扫描的包路径
    private String packageName;

    // 封装所有bean的容器
    private ConcurrentHashMap<String, Object> beans;

    // 创建时加载bean
    public SelfPathApplicationContext(String packageName) throws Exception {
        beans = new ConcurrentHashMap<>();
        this.packageName = packageName;
        initBeans();
        initEntryFiled();
    }

    public Object getBean(String beanId) throws Exception {
        if (beanId == null || beanId.length() == 0) {
            throw new Exception("beanId参数不能为空");
        }
        // 从存储容器中取出bean
        Object object = beans.get(beanId);
        return object;
    }

    /**
     * 为每一个bean注入相应的属性对象
     * @throws Exception
     */
    private void initEntryFiled() throws Exception {
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Object bean = entry.getValue();
            attributeAssign(bean);
        }
    }

    /**
     * 初始化扫描包下的所有bean
     * @throws Exception
     */
    private void initBeans() throws Exception {
        // 使用反射机制扫包，获取包中的所有类
        List<Class<?>> classes = ClassParseUtil.getClasses(packageName);
        // 判断类是不是需要初始化bean（有SelfComponent注解）
        ConcurrentHashMap<String, Object> classExistAnnotation = findClassExistAnnotation(classes);
        if (classExistAnnotation == null || classExistAnnotation.isEmpty()) {
            throw new Exception("该包下没有任何类加上注释进行初始化");
        }
    }

    private ConcurrentHashMap<String, Object> findClassExistAnnotation(List<Class<?>> classes) throws Exception {
        for(Class<?> classInfo : classes) {
            SelfComponent annotation = classInfo.getAnnotation(SelfComponent.class);
            if (annotation != null) {
                // 获取类名
                String className = classInfo.getSimpleName();
                String beanId = toLowerCharacter(className);
                Object newInstance = newInstance(classInfo);
                beans.put(beanId, newInstance);
            }
        }
        return beans;
    }

    /**
     * 将首字母转小写
     */
    private static String toLowerCharacter(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

    /**
     * 使用Class对象实例化对象
     * @param classInfo
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private Object newInstance(Class<?> classInfo)
            throws IllegalAccessException, InstantiationException {
        return classInfo.newInstance();
    }

    /**
     * 扫描注解并注入属性对象
     * @param bean
     */
    private void attributeAssign(Object bean) throws Exception {
        Class<?> classInfo = bean.getClass();
        // 1.反射获取全部属性
        Field[] declaredFields = classInfo.getDeclaredFields();
        for (Field field : declaredFields) {
            // 2.检测是否存在注入注释
            SelfAutowired annotation = field.getAnnotation(SelfAutowired.class);
            if (annotation != null) {
                // 获取属性名称
                String attributeName = field.getName();
                // 获得属性的bean
                Object attribute = getBean(attributeName);
                if (attribute != null) {
                    // 3.注入
                    field.setAccessible(true); // 设置允许访问私有属性
                    field.set(bean, attribute);
                }
            }
        }
    }
}

package beans;

import cn.hutool.core.util.ClassUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class SingletonBeanFacory {

    private InstantiationStrategy instantiationStrategy=new SimpleInstantiationStrategy();
    //bean缓存
    private HashMap singletonObjects = new HashMap<String,Object>();
    //beanDefination缓存
    private HashMap beanDefinations = new HashMap<String, BeanDefinition>();
    //BeanPostProcessor
    private List<BeanPostProcessor> beanPostProcessorList=new ArrayList<BeanPostProcessor>();
    //earlybean
    private HashMap earlySingletonObjects = new HashMap<String,Object>();

    public List<BeanPostProcessor> getBeanPostProcessorList() {
        return beanPostProcessorList;
    }

    public void addBeanPostProcesor(BeanPostProcessor beanPostProcessor){
        beanPostProcessorList.remove(beanPostProcessor);
        beanPostProcessorList.add(beanPostProcessor);
    }

    public void destroys(){
        for (Object o : singletonObjects.keySet()) {
            String beanName=(String)o;
            BeanDefinition bd= (BeanDefinition) beanDefinations.get(beanName);
            String destroyMethod=bd.getDestroy_method();
            if(destroyMethod!=null){
                Method method=ClassUtil.getPublicMethod(bd.getClazz(),destroyMethod);
                if(method!=null) {
                    try {
                        method.invoke(singletonObjects.get(beanName));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    //获取bean
    public Object getBean(String beanName){
        Object bean=null;
        BeanDefinition bd=getBeanDefination(beanName);
        if(bd.isSingleton()){
            try{
                Object o = singletonObjects.get(beanName);
                if(o!=null){
                    return o;
                }
                Object o1 = earlySingletonObjects.get(beanName);
                if(o1!=null){
                    return o1;
                }
                bean = createBean(beanName);
            }catch (BeanException be){
                be.printStackTrace();
            }
        }
        else {
            //多例循环依赖无法解决
            bean = createBean(beanName);
        }
        return bean;
    }

    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
        Map<String, T> result = new HashMap<>();
        Iterator iterator = beanDefinations.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,BeanDefinition> next = (Map.Entry<String, BeanDefinition>) iterator.next();
            Class clazz = next.getValue().getClazz();
            String benaName = next.getKey();
            //父isAssignableFrom子
            if(type.isAssignableFrom(clazz)){
                T bean = (T)getBean(benaName);
                result.put(benaName,bean);
            }
        }
        return result;
    }

    //添加bean到容器
    public void addSingleton(String beanName,Object bean){
        singletonObjects.put(beanName,bean);
    }

    //注册BeanDefination
    public void registerBeanDefination(String beanName, BeanDefinition bd){
        beanDefinations.put(beanName,bd);
    }
    public BeanDefinition getBeanDefination(String beanName){
        BeanDefinition beanDefinition = (BeanDefinition) beanDefinations.get(beanName);
        if(beanDefinition ==null){
            throw new BeanException("BeanDefination is not exit.");
        }
        return beanDefinition;
    }

    //创建bean
    private Object createBean(String beanName){
        BeanDefinition beanDefinition = getBeanDefination(beanName);

        //仅适用于存储在无参构造方法情况
//        Class clazz = beanDefinition.getClazz();
//        Object newInstance=null;
//        try {
//            newInstance  = clazz.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

        //调用构造方法创建
        Object newInstance = instantiationStrategy.instantiate(beanDefinition);
        earlySingletonObjects.put(beanName,newInstance);
        //赋值
        applyPropertyValue(newInstance,beanDefinition);
        //初始化
        initBean(beanName,newInstance,beanDefinition);
        addSingleton(beanName,newInstance);
        return newInstance;
    }

    public void applyPropertyValue(Object newInstance,BeanDefinition bd){
        PropertyValues propertyValues = bd.getPropertyValues();
        if (propertyValues==null){
            return;
        }
        PropertyValue[] properties =propertyValues.getProperties();
        try{
            for (PropertyValue pv: properties) {
                String name = pv.getName();
                Object value = pv.getValue();
                if(value instanceof PropertyReference){
                    String reference = ((PropertyReference) value).getReference();
                    value = this.getBean(reference);
                }
                Field field = newInstance.getClass().getDeclaredField(name);
                field.setAccessible(true);
                field.set(newInstance,value);
            }
        }catch (Exception e){
            new BeanException("applyPropertyValue wrong");
        }

    }

    public void initBean(String beanName,Object bean,BeanDefinition beanDefinition){
        for (BeanPostProcessor bpp : getBeanPostProcessorList()) {
            bpp.beforeBeanInit(beanName,bean);
        }
        //aware接口是前置处理器的一种，可在此处调用
        //执行InitMethod
        String init_method = beanDefinition.getInit_method();
        if(init_method!=null){
            Method publicMethod = ClassUtil.getPublicMethod(bean.getClass(), init_method);
            if(publicMethod!=null) {
                try {
                    publicMethod.invoke(bean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        for (BeanPostProcessor bpp : getBeanPostProcessorList()) {
            bpp.afterBeanInit(beanName,bean);
        }

    }

    public void preSingletonBean(){
        Set<String> set = beanDefinations.keySet();
        for (String beanName:set) {
            BeanDefinition beanDefinition = (BeanDefinition) beanDefinations.get(beanName);
            if(beanDefinition.isSingleton()){
                getBean(beanName);
            }
        }
    }
}

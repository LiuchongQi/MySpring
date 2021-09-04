package beans;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeanException {
        if(beanDefinition==null){
            return new BeanException("beanDefinition is null");
        }
        Object o=null;
        Class clazz = beanDefinition.getClazz();
        try {
            Constructor declaredConstructor = clazz.getDeclaredConstructor();
            o = declaredConstructor.newInstance();
        } catch (Exception e) {
            throw new BeanException("Fail to get Constructor");
        }
        return o;
    }
}

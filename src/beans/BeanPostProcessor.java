package beans;

public interface BeanPostProcessor {
    void beforeBeanInit(String beanName,Object instance);
    void afterBeanInit(String beanName,Object instance);
}

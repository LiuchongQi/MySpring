import beans.BeanPostProcessor;

public class CustomBean implements BeanPostProcessor {
    @Override
    public void beforeBeanInit(String beanName, Object instance) {
        System.out.println("------------------------beforeBeanInit------------------");
    }

    @Override
    public void afterBeanInit(String beanName, Object instance) {
        System.out.println("------------------------afterBeanInit------------------");
    }
}

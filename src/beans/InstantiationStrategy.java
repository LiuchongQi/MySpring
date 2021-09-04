package beans;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition) throws BeanException;
}

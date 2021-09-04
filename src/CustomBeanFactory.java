import beans.*;

public class CustomBeanFactory implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(SingletonBeanFacory beanFacory) {
        BeanDefinition car = beanFacory.getBeanDefination("car");
        PropertyValues propertyValues = car.getPropertyValues();
        propertyValues.addProperty(new PropertyValue("id","MyCar"));
    }
}

package context;

import LoadResources.XmlBeanDefinitionReader;
import beans.BeanFactoryPostProcessor;
import beans.BeanPostProcessor;
import beans.SingletonBeanFacory;
import io.DefaultResourceLoader;

import java.util.Map;

public class ClassPathXmlApplicationContext {
    private String configLoaction;
    private SingletonBeanFacory beanFacory;


    public ClassPathXmlApplicationContext(String configLoaction) {
        this.configLoaction=configLoaction;
        refresh();
    }

    public String getConfigLoaction(){
        return configLoaction;
    }

    public Object getBean(String beanName){
        return beanFacory.getBean(beanName);
    }
    public Object getBeanOfType(Class clazz){
        return beanFacory.getBeansOfType(clazz);
    }

    private void refresh(){
        refreshBeanFactory();
        invokeBeanFactoryPostProcessor(beanFacory);
        invokeBeanPostProcessor(beanFacory);
        beanFacory.preSingletonBean();
    }

    private void refreshBeanFactory() {
        SingletonBeanFacory singletonBeanFacory = new SingletonBeanFacory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(singletonBeanFacory, new DefaultResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(getConfigLoaction());
        this.beanFacory=singletonBeanFacory;
    }
    private void invokeBeanFactoryPostProcessor(SingletonBeanFacory beanFacory){
        Map<String, BeanFactoryPostProcessor> beansOfType = beanFacory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanfactoryPostProcessor:beansOfType.values()) {
            beanfactoryPostProcessor.postProcessBeanFactory(beanFacory);
        }
    }
    private void invokeBeanPostProcessor(SingletonBeanFacory beanFacory){
        Map<String, BeanPostProcessor> beansOfType = beanFacory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor: beansOfType.values()) {
            beanFacory.addBeanPostProcesor(beanPostProcessor);
        }
    }

    public void close(){
        beanFacory.destroys();
    }
}
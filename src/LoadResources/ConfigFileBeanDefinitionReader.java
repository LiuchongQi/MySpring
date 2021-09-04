package LoadResources;

import beans.SingletonBeanFacory;
import io.Resource;
import io.ResourceLoader;

public interface ConfigFileBeanDefinitionReader {
    void loadBeanDefinitions(Resource resource);
    void loadBeanDefinitions(String path);
    ResourceLoader getResourcesLoader();
    SingletonBeanFacory getBeanFactory();
}

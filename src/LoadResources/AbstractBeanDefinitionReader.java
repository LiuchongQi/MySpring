package LoadResources;

import beans.SingletonBeanFacory;
import io.Resource;
import io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements ConfigFileBeanDefinitionReader{
    private final SingletonBeanFacory bf;
    private ResourceLoader rl;

    public AbstractBeanDefinitionReader(SingletonBeanFacory bf, ResourceLoader rl) {
        this.bf = bf;
        this.rl = rl;
    }

    @Override
    public ResourceLoader getResourcesLoader() {
        return rl;
    }

    @Override
    public SingletonBeanFacory getBeanFactory() {
        return bf;
    }
}

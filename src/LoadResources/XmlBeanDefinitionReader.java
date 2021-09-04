package LoadResources;

import beans.*;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import io.Resource;
import io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(SingletonBeanFacory bf, ResourceLoader rl) {
        super(bf, rl);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) {
        try {
            InputStream inputStream = resource.getInputStream();
            doLoadBD(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadBeanDefinitions(String path) {
        Resource resource = getResourcesLoader().getResource(path);
        loadBeanDefinitions(resource);
    }

    private void doLoadBD(InputStream inputStream) {
        Document document = XmlUtil.readXML(inputStream);
        Element documentElement = document.getDocumentElement();
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item instanceof Element && "bean".equals(item.getNodeName())) {
                Element element = (Element) item;
                String id = element.getAttribute("id");

                String className = element.getAttribute("class");
                Class<?> aClass = null;
                try {
                    aClass = ClassLoader.getSystemClassLoader().loadClass(className);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (StrUtil.isEmpty(id)) {
                    id = StrUtil.lowerFirst(aClass.getSimpleName());
                }
                String scope=element.getAttribute("scrop");
                String init_method = element.getAttribute("init-method");
                String destroy_method = element.getAttribute("destroy-method");

                BeanDefinition beanDefinition = new BeanDefinition(aClass);
                PropertyValues propertyValues = new PropertyValues();
                NodeList nodes = element.getChildNodes();
                for (int j = 0; j < nodes.getLength(); j++) {
                    Node node = nodes.item(j);
                    if (node instanceof Element && "property".equals(node.getNodeName())) {
                        Element element1 = (Element) node;
                        String name = element1.getAttribute("name");
                        String value = element1.getAttribute("value");
                        String ref = element1.getAttribute("ref");
                        if (StrUtil.isEmpty(name)) {
                            throw new BeanException("name is empty");
                        }
                        Object attr = value;
                        if (!StrUtil.isEmpty(ref)) {
                            attr = new PropertyReference(ref);
                        }
                        propertyValues.addProperty(new PropertyValue(name, attr));
                    }
                }
                beanDefinition.setPropertyValues(propertyValues);
                beanDefinition.setInit_method(init_method);
                beanDefinition.setDestroy_method(destroy_method);
                beanDefinition.setScrop(scope);
                getBeanFactory().registerBeanDefination(id, beanDefinition);
            }
        }
    }
}

package beans;

public class BeanDefinition {
    public static String SCOPE_SINGLETON = "singleton";
    public static String SCOPE_PROTOTYPE = "prototype";
    private Class clazz;
    private PropertyValues propertyValues;
    private String init_method;
    private String scrop=SCOPE_SINGLETON;

    public boolean isSingleton() {
        return isSingleton;
    }

    private boolean isSingleton=true;

    public void setScrop(String scrop) {
        if(SCOPE_SINGLETON.equals(scrop)){
            this.scrop=SCOPE_SINGLETON;
            this.isSingleton=true;
        }
        else if(SCOPE_PROTOTYPE.equals(scrop)){
            this.scrop=SCOPE_PROTOTYPE;
            this.isSingleton=false;
        }
    }

    public String getInit_method() {
        return init_method;
    }

    public void setInit_method(String init_method) {
        this.init_method = init_method;
    }

    public String getDestroy_method() {
        return destroy_method;
    }

    public void setDestroy_method(String destroy_method) {
        this.destroy_method = destroy_method;
    }

    private String destroy_method;

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
    public BeanDefinition(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}

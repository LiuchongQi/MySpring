import LoadResources.XmlBeanDefinitionReader;
import beans.SingletonBeanFacory;
import context.ClassPathXmlApplicationContext;
import io.DefaultResourceLoader;

public class App {
    public static void main(String[] args) {

        //circle
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Car car = (Car)classPathXmlApplicationContext.getBean("car");
        Person person = (Person)classPathXmlApplicationContext.getBean("person");
        System.out.println(car);
        System.out.println(person);

        //prototype bean
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
//        Person person = (Person)classPathXmlApplicationContext.getBean("person");
//        Person person2 = (Person)classPathXmlApplicationContext.getBean("person");
//        System.out.println(person);
//        System.out.println(person2);


        //init and destroy
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
//        Car car = (Car) classPathXmlApplicationContext.getBean("car");
//        Person person = (Person) classPathXmlApplicationContext.getBean("person");
//        System.out.println(car.getId());
//        System.out.println(car.getPerson());
//        System.out.println(person);
//        classPathXmlApplicationContext.close();

        //applicationContext
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
//        Car car = (Car) classPathXmlApplicationContext.getBean("car");
//        Person person = (Person) classPathXmlApplicationContext.getBean("person");
//        System.out.println(car.getId());
//        System.out.println(car.getPerson());
//        System.out.println(person);

        //beanPostProcessor
//        SingletonBeanFacory singletonBeanFacory = new SingletonBeanFacory();
//        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(singletonBeanFacory, new DefaultResourceLoader());
//        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
//        CustomBean customBean = new CustomBean();
//        singletonBeanFacory.addBeanPostProcesor(customBean);
//        Car car = (Car) singletonBeanFacory.getBean("car");
//        Person person = (Person) singletonBeanFacory.getBean("person");
//        System.out.println(car.getId());
//        System.out.println(car.getPerson());
//        System.out.println(person);

        //beanFactoryPostProcessor
//        SingletonBeanFacory singletonBeanFacory = new SingletonBeanFacory();
//        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(singletonBeanFacory, new DefaultResourceLoader());
//        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
//        CustomBeanFactory customBeanFactory = new CustomBeanFactory();
//        customBeanFactory.postProcessBeanFactory(singletonBeanFacory);
//        Car car = (Car) singletonBeanFacory.getBean("car");
//        Person person = (Person) singletonBeanFacory.getBean("person");
//        System.out.println(car.getId());
//        System.out.println(car.getPerson());
//        System.out.println(person);

//        SingletonBeanFacory singletonBeanFacory = new SingletonBeanFacory();
//        BeanDefinition beanDefinition = new BeanDefinition(Car.class);
//        PropertyValues propertyValues = new PropertyValues();
//        propertyValues.addProperty(new PropertyValue("id","qwee"));
//        propertyValues.addProperty(new PropertyValue("person",new PropertyReference("person")));
//        beanDefinition.setPropertyValues(propertyValues);
//        singletonBeanFacory.registerBeanDefination("car",beanDefinition);
//        singletonBeanFacory.registerBeanDefination("person",new BeanDefinition(Person.class));
//        Car car = (Car) singletonBeanFacory.getBean("car");
//        System.out.println(car.getId());
//        System.out.println(car.getPerson());

        //获取资源
//        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
//        Resource resource = defaultResourceLoader.getResource("classpath:hello.txt");
//        try {
//            InputStream inputStream = resource.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            String s = bufferedReader.readLine();
//            System.out.println(s);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
            //xml初始化bean
//        SingletonBeanFacory singletonBeanFacory = new SingletonBeanFacory();
//        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(singletonBeanFacory, new DefaultResourceLoader());
//        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
//        Car car = (Car) singletonBeanFacory.getBean("car");
//        Person person = (Person) singletonBeanFacory.getBean("person");
//        System.out.println(car.getPerson());
//        System.out.println(person);
    }
}

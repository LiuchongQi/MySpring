package io;

public class DefaultResourceLoader implements ResourceLoader{
    private final String CLASSPATH="classpath:";
    @Override
    public Resource getResource(String location) {
        if(location.startsWith(CLASSPATH)){
            return new ClassPathResource(location.substring(CLASSPATH.length()));
        }
        return null;
    }
}

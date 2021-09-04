package beans;

import java.util.ArrayList;

public class PropertyValues {
    private ArrayList<PropertyValue> values=new ArrayList<PropertyValue>();
    public void addProperty(PropertyValue p){
        values.add(p);
    }
    public PropertyValue[] getProperties(){
        PropertyValue[] propertyValues = values.toArray(new PropertyValue[0]);
        return propertyValues;
    }
    public PropertyValue getPropertyValue(String name){
        for (PropertyValue pv : values) {
            if(pv.getName().equals(name)){
                return pv;
            }
        }
        return null;
    }
}

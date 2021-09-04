public class Person {
    public void initMethod(){
        System.out.println("初始化person");
    }
    public void destroyMethod(){
        System.out.println("销毁person");
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    private Car car;

}

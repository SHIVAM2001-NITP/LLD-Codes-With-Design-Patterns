import Car.Car;

public class Main {
    public static void main(String[] args){

        AbstractFactoryProducer abstractFactoryProducerObj=new AbstractFactoryProducer();
        AbstractFactory abstractFactoryObj=abstractFactoryProducerObj.getFactoryInstance("Premium");
        Car carObj=abstractFactoryObj.getInstance(500000);
        System.out.println(carObj.getTopSpeed());
    }
}

import Decorator.ExtraCheeseDecorator;
import Decorator.MushroomDecorator;
import Pizza.BasePizza;
import Pizza.Margherita;

public class Main {
    public static void main(String []args){
        //Margherita+ExtraCheese
        BasePizza Pizza1=new ExtraCheeseDecorator(new Margherita());
       System.out.println("Pizza1 cost is: "+Pizza1.cost());

        //Mushroom+Margherita+ExtraCheese
        BasePizza Pizza2=new MushroomDecorator( new ExtraCheeseDecorator(new Margherita()));
        System.out.println("Pizza2 cost is: "+Pizza2.cost());

    }
}

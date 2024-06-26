package Decorator;
import Pizza.BasePizza;

public class ExtraCheeseDecorator extends ToppingDecorator{
    BasePizza basePizza;

    public ExtraCheeseDecorator(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public int cost() {
        return this.basePizza.cost()+10;
    }
}

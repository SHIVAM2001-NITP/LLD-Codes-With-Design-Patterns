package Decorator;

import Pizza.BasePizza;

public class MushroomDecorator extends ToppingDecorator{
    BasePizza basePizza;

    public MushroomDecorator(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public int cost() {
        return basePizza.cost()+15;
    }
}

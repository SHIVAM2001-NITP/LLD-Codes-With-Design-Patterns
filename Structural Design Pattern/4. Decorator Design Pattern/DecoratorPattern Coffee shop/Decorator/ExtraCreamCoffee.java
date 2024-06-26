package com.DecoratorPattern.Decorator;

import com.DecoratorPattern.Coffee.Coffee;

public class ExtraCreamCoffee extends DecoratorCoffee {

	public ExtraCreamCoffee(Coffee coffe) {
		super(coffe);
		
	}

	@Override
	public void make() {
		coffee.make();
		System.out.println(" + extra cream added to the normal coffee");
		
	}

	@Override
	public int price() {
		
		return coffee.price()+50;
	}

}

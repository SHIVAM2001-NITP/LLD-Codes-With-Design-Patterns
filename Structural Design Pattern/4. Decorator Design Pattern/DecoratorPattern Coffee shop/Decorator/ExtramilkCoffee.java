package com.DecoratorPattern.Decorator;

import com.DecoratorPattern.Coffee.Coffee;

public class ExtramilkCoffee extends DecoratorCoffee {
	
	public ExtramilkCoffee(Coffee coffe){
		super(coffe);
	}
	@Override
	public void make() {
		coffee.make();
		System.out.println(" + extra milk added to the normal coffee");
		
	}

	@Override
	public int price() {
		
		return coffee.price()+20;
	}

}

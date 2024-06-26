package com.DecoratorPattern;


import com.DecoratorPattern.Decorator.ExtraCreamCoffee;
import com.DecoratorPattern.Coffee.BaseCoffee;
import com.DecoratorPattern.Coffee.Coffee;
import com.DecoratorPattern.Decorator.ExtramilkCoffee;

public class Main {

	public static void main(String[] args) {
		System.out.println("Base coffe details ");
		
		Coffee c=new BaseCoffee();
		c.make();
		System.out.println(c.price());
	
		System.out.println("exta milk coffee details");
		ExtramilkCoffee em=new ExtramilkCoffee(c);
		em.make();
		System.out.println(em.price());
		
		System.out.println("extra cream coffee details");
		ExtraCreamCoffee ec=new ExtraCreamCoffee(c);
		ec.make();
		System.out.println(ec.price());
		
		System.out.println("coffee with extra cream and extra milk");
		Coffee emc=new ExtraCreamCoffee(new ExtramilkCoffee(new BaseCoffee()));
		emc.make();
		System.out.println(emc.price());
				
	}

}

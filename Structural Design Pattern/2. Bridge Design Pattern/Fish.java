package com.bridgedesignpattern;

//refined abstractions
public class Fish extends LivingThings{

	public Fish(BreathingSystemImplementer breathingSystem) {
		super(breathingSystem);
		
	}

	@Override
	public void breathProcess() {
		breathingSystem.breath();
	}


}

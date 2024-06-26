package com.bridgedesignpattern;


//refined abstractions
public class HumanBeing extends LivingThings{

	public HumanBeing(BreathingSystemImplementer breathingSystem) {
		super(breathingSystem);
		
	}

	@Override
	public void breathProcess() {
		breathingSystem.breath();
	}

}

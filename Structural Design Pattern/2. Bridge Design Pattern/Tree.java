package com.bridgedesignpattern;

//refined abstractions
public class Tree extends LivingThings{

	public Tree(BreathingSystemImplementer breathingSystem) {
		super(breathingSystem);
		
	}

	@Override
	public void breathProcess() {
		breathingSystem.breath();
	}


}

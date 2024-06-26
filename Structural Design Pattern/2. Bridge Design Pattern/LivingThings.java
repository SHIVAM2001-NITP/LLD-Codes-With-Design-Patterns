package com.bridgedesignpattern;

public abstract class LivingThings {
	
	public BreathingSystemImplementer breathingSystem;
	
	public LivingThings(BreathingSystemImplementer breathingSystem) {
		this.breathingSystem=breathingSystem;
	}
	
	 abstract public void breathProcess();

}

package com.bridgedesignpattern;

public class BridgeMain {

	public static void main(String[] args) {
		HumanBeing human = new HumanBeing(new LandBreathSystem());
		human.breathProcess();
		LivingThings fish= new Fish(new WaterBreathSystem());
		fish.breathProcess();

	}

}

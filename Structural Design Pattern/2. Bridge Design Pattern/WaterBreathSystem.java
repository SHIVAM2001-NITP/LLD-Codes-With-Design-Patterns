package com.bridgedesignpattern;

//concrete implementers
public class WaterBreathSystem implements BreathingSystemImplementer {

	@Override
	public void breath() {
		String breathingOfTree="""
				Breathing through leaves
				Exhaling Oxygen
				Inhaling CO2
				""";
		System.out.println(breathingOfTree);

	}

}

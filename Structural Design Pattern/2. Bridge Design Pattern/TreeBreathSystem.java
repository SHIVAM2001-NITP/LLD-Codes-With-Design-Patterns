package com.bridgedesignpattern;

//concrete implementer
public class TreeBreathSystem implements BreathingSystemImplementer {

	@Override
	public void breath() {
		String breathingInWater="""
				Breathing through grills
				Inhaling Oxygen
				Exhaling CO2
				""";
		System.out.println(breathingInWater);

	}

}

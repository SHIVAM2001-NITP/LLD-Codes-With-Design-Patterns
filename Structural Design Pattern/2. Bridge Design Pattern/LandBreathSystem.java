package com.bridgedesignpattern;

//concrete implementers
public class LandBreathSystem implements BreathingSystemImplementer {

	@Override
	public void breath() {
		String breathingOnLand="""
				Breathing through nose
				Inhaling Oxygen
				Exhaling CO2
				""";
		System.out.println(breathingOnLand);

	}

}

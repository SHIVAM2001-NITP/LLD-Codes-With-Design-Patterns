package com.WithoutCommandDesignPatternRemoteControlWithBulb;

public class Bulb {
    boolean isOn;
    public void turnOnBulb(){
        isOn=true;
        System.out.println("Bulb is ON");
    }
    public void turnOffBulb(){
        isOn=false;
        System.out.println("AC is OFF");
    }


}

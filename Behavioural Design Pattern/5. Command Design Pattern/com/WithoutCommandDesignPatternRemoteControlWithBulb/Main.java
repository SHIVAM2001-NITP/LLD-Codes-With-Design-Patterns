package com.WithoutCommandDesignPatternRemoteControlWithBulb;

public class Main {
    public static void main(String args[]){
        AirConditioner ac=new AirConditioner();
        ac.turnOnAc();
        ac.setTemperature(24);
        ac.turnOffAc();

        Bulb bulbObj=new Bulb();
        bulbObj.turnOnBulb();
        bulbObj.turnOffBulb();

    }
}

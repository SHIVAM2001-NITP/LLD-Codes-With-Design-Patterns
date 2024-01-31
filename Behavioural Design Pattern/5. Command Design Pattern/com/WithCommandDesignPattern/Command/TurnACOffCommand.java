package com.WithCommandDesignPattern.Command;

import com.WithCommandDesignPattern.Receiver.AirConditioner;

public class TurnACOffCommand implements ICommand{
    AirConditioner ac;
    TurnACOffCommand(AirConditioner ac){
        this.ac=ac;
    }
    @Override
    public void execute(){
        ac.turnOffAc();
    }
}

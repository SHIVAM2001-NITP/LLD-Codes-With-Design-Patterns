package com.WithCommandDesignPattern.Command;


import com.WithCommandDesignPattern.Receiver.AirConditioner;

public class TurnACOnCommand implements ICommand{

    AirConditioner ac;
    public TurnACOnCommand(AirConditioner ac){
        this.ac=ac;
    }
    @Override
    public void execute(){
       ac.turnOnAc();
    }
}

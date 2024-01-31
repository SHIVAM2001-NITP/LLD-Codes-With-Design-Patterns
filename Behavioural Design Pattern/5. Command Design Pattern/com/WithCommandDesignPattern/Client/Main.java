package com.WithCommandDesignPattern.Client;

import com.WithCommandDesignPattern.Command.TurnACOnCommand;
import com.WithCommandDesignPattern.Invoker.MyRemoteControl;
import com.WithCommandDesignPattern.Receiver.AirConditioner;

public class Main {
    public static void main(String[] args){
       // AC Object
        AirConditioner airConditioner=new AirConditioner();

        //remote
        MyRemoteControl remoteControl=new MyRemoteControl();

        //create the Command and press the button
        remoteControl.setCommand(new TurnACOnCommand(airConditioner));
        remoteControl.pressButton();

    }
}

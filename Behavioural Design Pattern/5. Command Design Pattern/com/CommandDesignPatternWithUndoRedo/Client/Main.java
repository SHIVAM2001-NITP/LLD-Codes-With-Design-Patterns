package com.CommandDesignPatternWithUndoRedo.Client;


import com.CommandDesignPatternWithUndoRedo.Commad.TurnACOnCommand;
import com.CommandDesignPatternWithUndoRedo.Invoker.MyRemoteControl;
import com.CommandDesignPatternWithUndoRedo.Receiver.AirConditioner;

public class Main {
    public static void main(String[] args){
        // AC Object
        AirConditioner airConditioner=new AirConditioner();

        //remote
        MyRemoteControl remoteControl=new MyRemoteControl();

        //create the Command and press the button
        remoteControl.setCommand(new TurnACOnCommand(airConditioner));
        remoteControl.pressButton();

        //undo the last operation
        remoteControl.undo();

    }
}

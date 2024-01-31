package com.CommandDesignPatternWithUndoRedo.Commad;


import com.CommandDesignPatternWithUndoRedo.Receiver.AirConditioner;

public class TurnACOnCommand implements ICommand {

    AirConditioner ac;
    public TurnACOnCommand(AirConditioner ac){
        this.ac=ac;
    }
    @Override
    public void execute(){
       ac.turnOnAc();
    }
    @Override
    public void undo(){
        ac.turnOffAc();
    }
}

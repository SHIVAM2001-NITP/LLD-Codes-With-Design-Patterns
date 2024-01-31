package com.WithCommandDesignPattern.Invoker;

import com.WithCommandDesignPattern.Command.ICommand;

public class MyRemoteControl {
    ICommand command;
    public MyRemoteControl(){

    }
    public void setCommand(ICommand command){
        this.command=command;
    }
    public void pressButton(){
        command.execute();
    }
}

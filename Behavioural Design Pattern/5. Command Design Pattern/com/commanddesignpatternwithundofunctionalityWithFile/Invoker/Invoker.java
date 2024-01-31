package com.commanddesignpatternwithundofunctionalityWithFile.Invoker;

import com.commanddesignpatternwithundofunctionalityWithFile.Command.Command;

import java.util.Stack;

public class Invoker {
	
	Command command;
	
	Stack<Command> addCommandHistory=new Stack<>();
	public void setCommand(Command command) {
		this.command=command;
	}
	
	public void performAction() {
		this.command.execute();
		this.addCommandHistory.add(command);
	}
	
	public void undoAction() {
		if(!addCommandHistory.isEmpty()){
			Command lastCommand = addCommandHistory.pop();
			lastCommand.undo();
		}
		else {
			System.out.println("no action performed earlier");
		}
	}

}

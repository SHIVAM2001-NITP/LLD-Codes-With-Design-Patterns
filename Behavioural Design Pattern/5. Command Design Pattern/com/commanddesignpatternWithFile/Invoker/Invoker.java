package com.commanddesignpatternWithFile.Invoker;

import com.commanddesignpatternWithFile.Command.Command;

public class Invoker {
	
	Command command;
	public void setCommand(Command command) {
		this.command=command;
	}
	
	public void performAction() {
		this.command.execute();
	}

}
